package com.wtulich.photosupp.serviceordering.logic.impl.usecase;

import com.wtulich.photosupp.general.logic.api.exception.EntityAlreadyExistsException;
import com.wtulich.photosupp.general.logic.api.exception.EntityDoesNotExistException;
import com.wtulich.photosupp.serviceordering.dataaccess.api.dao.*;
import com.wtulich.photosupp.serviceordering.dataaccess.api.entity.*;
import com.wtulich.photosupp.serviceordering.logic.api.mapper.*;
import com.wtulich.photosupp.serviceordering.logic.api.to.*;
import com.wtulich.photosupp.serviceordering.logic.api.usecase.UcManageBooking;
import com.wtulich.photosupp.serviceordering.logic.impl.validator.AddressValidator;
import com.wtulich.photosupp.serviceordering.logic.impl.validator.BookingValidator;
import com.wtulich.photosupp.userhandling.dataaccess.api.dao.PermissionDao;
import com.wtulich.photosupp.userhandling.dataaccess.api.dao.RoleDao;
import com.wtulich.photosupp.userhandling.dataaccess.api.dao.UserDao;
import com.wtulich.photosupp.userhandling.dataaccess.api.entity.PermissionEntity;
import com.wtulich.photosupp.userhandling.dataaccess.api.entity.RoleEntity;
import com.wtulich.photosupp.userhandling.dataaccess.api.entity.UserEntity;
import com.wtulich.photosupp.userhandling.logic.api.mapper.AccountMapper;
import com.wtulich.photosupp.userhandling.logic.api.mapper.PermissionsMapper;
import com.wtulich.photosupp.userhandling.logic.api.mapper.RoleMapper;
import com.wtulich.photosupp.userhandling.logic.api.mapper.UserMapper;
import com.wtulich.photosupp.userhandling.logic.api.to.RoleEto;
import com.wtulich.photosupp.userhandling.logic.api.to.RoleTo;
import com.wtulich.photosupp.userhandling.logic.api.to.UserEto;
import com.wtulich.photosupp.userhandling.logic.impl.usecase.UcManageRoleImpl;
import com.wtulich.photosupp.userhandling.logic.impl.validator.RoleValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Validated
@Named
public class UcManageBookingImpl implements UcManageBooking {

    private static final Logger LOG = LoggerFactory.getLogger(UcManageBookingImpl.class);
    private static final String ID_CANNOT_BE_NULL = "id cannot be a null value";
    private static final String CREATE_BOOKING_LOG = "Create Booking with name {} in database.";
    private static final String CREATE_ADDRESS_LOG = "Create Address in database.";
    private static final String UPDATE_BOOKING_LOG = "Update Booking with id {} from database.";
    private static final String UPDATE_ADDRESS_LOG = "Update Address in database.";

    @Inject
    private BookingDao bookingDao;

    @Inject
    private AddressDao addressDao;

    @Inject
    private ServiceDao serviceDao;

    @Inject
    private IndicatorDao indicatorDao;

    @Inject
    private PriceIndicatorDao priceIndicatorDao;

    @Inject
    private UserDao userDao;

    @Inject
    private BookingMapper bookingMapper;

    @Inject
    private AddressMapper addressMapper;

    @Inject
    private ServiceMapper serviceMapper;

    @Inject
    private IndicatorMapper indicatorMapper;

    @Inject
    private RoleMapper roleMapper;

    @Inject
    private AccountMapper accountMapper;

    @Inject
    private PermissionsMapper permissionsMapper;

    @Inject
    private BookingValidator bookingValidator;

    @Inject
    private AddressValidator addressValidator;

    @Inject
    private UserMapper userMapper;


    @Override
    public Optional<BookingEto> createBooking(BookingTo bookingTo) throws EntityAlreadyExistsException, EntityDoesNotExistException {
        bookingValidator.verifyIfBookingNameAlreadyExists(bookingTo.getName());
        bookingValidator.verifyIfBookingAlreadyCreatedAtThatDate(bookingTo);
        LOG.debug(CREATE_BOOKING_LOG, bookingTo.getName());

        BookingEntity bookingEntity = bookingDao.save(toBookingEntity(bookingTo));

        if( bookingTo.getPriceIndicatorToList() != null ){
            bookingEntity.setPriceIndicatorList(createPriceIndicatorList(bookingTo.getPriceIndicatorToList(), bookingEntity));
        }

        bookingEntity.setPredictedPrice
                (calculatePredictedPrice
                        (bookingEntity.getPriceIndicatorList(), bookingEntity.getService()));

        bookingEntity.setConfirmed(false);
        bookingEntity.setModificationDate(LocalDate.now());

        return toBookingEto(bookingEntity);
    }

    @Override
    public Optional<BookingEto> updateBooking(BookingTo bookingTo, Long id)
            throws EntityDoesNotExistException, EntityAlreadyExistsException {

        Objects.requireNonNull(id, ID_CANNOT_BE_NULL);

        BookingEntity bookingEntity = bookingDao.findById(id).orElseThrow(() ->
                new EntityDoesNotExistException("Booking with id " + id + " does not exist."));

        LOG.debug(UPDATE_BOOKING_LOG, id);

        return toBookingEto(mapBookingEntity(bookingEntity, bookingTo));
    }

    private BookingEntity toBookingEntity(BookingTo bookingTo) throws EntityDoesNotExistException {
        BookingEntity bookingEntity = bookingMapper.toBookingEntity(bookingTo);

        bookingEntity.setService(getServiceById(bookingTo.getServiceId()));
        bookingEntity.setUser(getUserById(bookingTo.getUserId()));

        if( bookingTo.getAddressTo() != null ) {
            bookingEntity.setAddress(getAddress(bookingTo.getAddressTo()));
        }

        return bookingEntity;
    }

    private Optional<BookingEto> toBookingEto(BookingEntity bookingEntity){
        BookingEto bookingEto = bookingMapper.toBookingEto(bookingEntity);

        bookingEto.setServiceEto(serviceMapper.toServiceEto(bookingEntity.getService()));
        bookingEto.setUserEto(toUserEto(bookingEntity.getUser()));

        if( bookingEntity.getAddress() != null ){
            bookingEto.setAddressEto(addressMapper.toAddressEto(bookingEntity.getAddress()));
        }

        if( bookingEntity.getPriceIndicatorList() != null ) {
            bookingEto.setPriceIndicatorEtoList(
                    bookingEntity.getPriceIndicatorList().stream()
                            .map(priceIndicatorEntity ->
                                    new PriceIndicatorEto(
                                            indicatorMapper.toIndicatorEto(priceIndicatorEntity.getIndicator()),
                                            bookingEntity.getId(),
                                            priceIndicatorEntity.getIndicatorPrice(),
                                            priceIndicatorEntity.getMultiplier()
                                    )).collect(Collectors.toList()));
        }

        return Optional.of(bookingEto);
    }

    private ServiceEntity getServiceById(Long serviceId) throws EntityDoesNotExistException {
        return serviceDao.findById(serviceId).orElseThrow(() ->
                new EntityDoesNotExistException("Service with id " + serviceId + " does not exist."));
    }

    private UserEntity getUserById(Long userId) throws EntityDoesNotExistException {
        return userDao.findById(userId).orElseThrow(() ->
                new EntityDoesNotExistException("User with id " + userId + " does not exist."));
    }

    private AddressEntity getAddress(AddressTo addressTo) {
        AddressEntity addressEntity;

        if( addressValidator.isAddressAlreadyExists(addressTo) ) {
            addressEntity = addressDao.findByCityAndStreetAndBuildingNumberAndApartmentNumberAndPostalCode
                    (addressTo.getCity(), addressTo.getStreet(), addressTo.getBuildingNumber(),
                            addressTo.getApartmentNumber(), addressTo.getPostalCode());
        } else {
            LOG.debug(CREATE_ADDRESS_LOG);
            addressEntity = addressDao.save(addressMapper.toAddressEntity(addressTo));
        }

        return addressEntity;
    }

    private List<PriceIndicatorEntity> createPriceIndicatorList
            (List<PriceIndicatorTo> priceIndicatorToList, BookingEntity bookingEntity) throws EntityDoesNotExistException {
        List<PriceIndicatorEntity> priceIndicatorEntityList = new ArrayList<>();

        for (PriceIndicatorTo priceIndicatorTo : priceIndicatorToList) {
            priceIndicatorEntityList.add(priceIndicatorDao.save(toPriceIndicatorEntity(priceIndicatorTo, bookingEntity)));
        }

        return priceIndicatorEntityList;
    }

    private PriceIndicatorEntity toPriceIndicatorEntity(PriceIndicatorTo priceIndicatorTo, BookingEntity bookingEntity)
            throws EntityDoesNotExistException {

        PriceIndicatorEntity priceIndicatorEntity = new PriceIndicatorEntity();
        IndicatorEntity indicatorEntity = getIndicatorById(priceIndicatorTo.getIndicatorId());

        priceIndicatorEntity.setBooking(bookingEntity);
        priceIndicatorEntity.setIndicator(indicatorEntity);
        priceIndicatorEntity.setMultiplier(priceIndicatorEntity.getMultiplier());
        priceIndicatorEntity.setIndicatorPrice(indicatorEntity.getBaseAmount() * priceIndicatorTo.getMultiplier());

        return priceIndicatorEntity;
    }

    private IndicatorEntity getIndicatorById(Long indicatorId) throws EntityDoesNotExistException {
        return indicatorDao.findById(indicatorId).orElseThrow(() ->
                new EntityDoesNotExistException("Indicator with id " + indicatorId + " does not exist."));
    }

    private BookingEntity mapBookingEntity(BookingEntity bookingEntity, BookingTo bookingTo)
            throws EntityDoesNotExistException, EntityAlreadyExistsException {

        if(!bookingEntity.getName().equals(bookingTo.getName())){
            bookingValidator.verifyIfBookingNameAlreadyExists(bookingEntity.getName());
            bookingEntity.setName(bookingTo.getName());
        }

        if(!(DateTimeFormatter.ofPattern( "yyyy-MM-dd" ).format(bookingEntity.getStart()).equals(bookingTo.getStart()) &&
                DateTimeFormatter.ofPattern( "yyyy-MM-dd" ).format(bookingEntity.getEnd()).equals(bookingTo.getEnd()))) {

            bookingValidator.verifyIfBookingAlreadyCreatedAtThatDate(bookingTo);
            bookingEntity.setStart( LocalDate.parse(bookingTo.getStart()) );
            bookingEntity.setEnd( LocalDate.parse(bookingTo.getEnd()) );
        }

        bookingEntity.setDescription(bookingEntity.getDescription());

        if( !bookingEntity.getService().getId().equals(bookingTo.getServiceId()) ) {
            bookingEntity.setService(getServiceById(bookingTo.getServiceId()));
        }

        AddressEntity addressEntityTest = addressMapper.toAddressEntity(bookingTo.getAddressTo());
        addressEntityTest.setId(bookingEntity.getAddress().getId());

        if(!bookingEntity.getAddress().equals(addressEntityTest)) {
            bookingEntity.setAddress(updateAddress(bookingEntity.getAddress(), bookingTo.getAddressTo()));

        } else if ( bookingTo.getAddressTo() == null ){
            bookingEntity.setAddress(null);
        }

        if( bookingTo.getPriceIndicatorToList() != null ){

            bookingEntity.getPriceIndicatorList().stream()
                    .forEach(priceIndicatorEntity -> priceIndicatorDao.delete(priceIndicatorEntity));

            bookingEntity.setPriceIndicatorList(createPriceIndicatorList(bookingTo.getPriceIndicatorToList(), bookingEntity));

        } else {
            bookingEntity.setPriceIndicatorList(null);
        }

        bookingEntity.setPredictedPrice
                (calculatePredictedPrice
                        (bookingEntity.getPriceIndicatorList(), bookingEntity.getService()));

        bookingEntity.setModificationDate( LocalDate.now() );

        return bookingEntity;
    }

    private AddressEntity updateAddress(AddressEntity addressEntity, AddressTo addressTo){

        if( addressValidator.isAddressAlreadyExists(addressTo) ) {
            addressEntity = addressDao.findByCityAndStreetAndBuildingNumberAndApartmentNumberAndPostalCode
                    (addressTo.getCity(), addressTo.getStreet(), addressTo.getBuildingNumber(),
                            addressTo.getApartmentNumber(), addressTo.getPostalCode());
        } else {
            LOG.debug(UPDATE_ADDRESS_LOG);
            addressEntity.setCity(addressTo.getCity());
            addressEntity.setStreet(addressTo.getStreet());
            addressEntity.setBuildingNumber(addressTo.getBuildingNumber());
            addressEntity.setApartmentNumber(addressTo.getApartmentNumber());
            addressEntity.setPostalCode(addressTo.getPostalCode());
        }

        return addressEntity;
    }

    private Double calculatePredictedPrice(List<PriceIndicatorEntity> priceIndicatorEntityList, ServiceEntity serviceEntity){
        Double predictedPrice = serviceEntity.getBasePrice();

        if (priceIndicatorEntityList != null){
            for (PriceIndicatorEntity priceIndicator : priceIndicatorEntityList) {
                predictedPrice += priceIndicator.getIndicatorPrice();
            }
        }

        return  predictedPrice;
    }

    private UserEto toUserEto(UserEntity userEntity){
        UserEto userEto = userMapper.toUserEto(userEntity);
        userEto.setAccountEto(accountMapper.toAccountEto(userEntity.getAccount()));

        RoleEto roleEto = roleMapper.toRoleEto(userEntity.getRole());
        roleEto.setPermissionEtoList(userEntity.getRole().getPermissions().stream()
                .map(p -> permissionsMapper.toPermissionEto(p))
                .collect(Collectors.toList()));
        userEto.setRoleEto(roleEto);
        return userEto;
    }
}
