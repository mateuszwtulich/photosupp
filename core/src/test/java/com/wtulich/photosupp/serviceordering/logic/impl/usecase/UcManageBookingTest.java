package com.wtulich.photosupp.serviceordering.logic.impl.usecase;

import com.wtulich.photosupp.general.logic.api.exception.EntityAlreadyExistsException;
import com.wtulich.photosupp.general.logic.api.exception.EntityDoesNotExistException;
import com.wtulich.photosupp.general.security.enums.ApplicationPermissions;
import com.wtulich.photosupp.serviceordering.dataaccess.api.dao.BookingDao;
import com.wtulich.photosupp.serviceordering.dataaccess.api.dao.ServiceDao;
import com.wtulich.photosupp.serviceordering.dataaccess.api.entity.*;
import com.wtulich.photosupp.serviceordering.logic.api.to.*;
import com.wtulich.photosupp.serviceordering.logic.impl.validator.BookingValidator;
import com.wtulich.photosupp.userhandling.dataaccess.api.entity.UserEntity;
import com.wtulich.photosupp.userhandling.logic.api.to.AccountEto;
import com.wtulich.photosupp.userhandling.logic.api.to.PermissionEto;
import com.wtulich.photosupp.userhandling.logic.api.to.RoleEto;
import com.wtulich.photosupp.userhandling.logic.api.to.UserEto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class})
@SpringBootTest
@AutoConfigureMockMvc
public class UcManageBookingTest {

    @Autowired
    private UcManageBookingImpl ucManageBooking;

    @MockBean
    private BookingDao bookingDao;

    @MockBean
    private ServiceDao serviceDao;

    @MockBean
    private BookingValidator bookingValidator;

    private BookingEntity bookingEntity;
    private BookingEto bookingEto;
    private BookingTo bookingTo;

    @BeforeEach
    void setUp() {
        AddressEto addressEto = new AddressEto(1L, "Wroclaw", "Wroblewskiego", "27", null, "51-627");
        ServiceEto serviceEto = new ServiceEto(1L, "Film produktowy", "Film produktow na bialym tle i odpowiednim oswietleniu", 500D);
        IndicatorEto indicatorEto = new IndicatorEto(1L, "Podroz sluzbowa", "Paliwo, amortyzacja", 40);

        List<PermissionEto> permissionEtoList = new ArrayList<>();
        permissionEtoList.add(new PermissionEto(1L, ApplicationPermissions.A_CRUD_SUPER, "DESC1"));
        RoleEto roleEto = new RoleEto(1L, "ADMIN", "DESC1", permissionEtoList);
        AccountEto accountEto = new AccountEto(1L, "TEST", "PASS", "TEST@test.com", false);
        UserEto userEto = new UserEto(1L, "NAME1", "SURNAME1", accountEto, roleEto);

        List<PriceIndicatorEto> priceIndicatorEtoList = new ArrayList<>();
        bookingEto = new BookingEto(1L, "Film dla TestCompany", "Film produktowy z dojazdem", serviceEto, addressEto, userEto, false, 900D,
                DateTimeFormatter.ofPattern( "yyyy-MM-dd" ).format( getCurrentDate(LocalDate.now(),0)),
                DateTimeFormatter.ofPattern( "yyyy-MM-dd" ).format( getCurrentDate(LocalDate.now(),1)),
                DateTimeFormatter.ofPattern( "yyyy-MM-dd" ).format( getCurrentDate(LocalDate.now(),0)),
                priceIndicatorEtoList);

        PriceIndicatorEto priceIndicatorEto = new PriceIndicatorEto(indicatorEto, bookingEto, 400,10);
        priceIndicatorEtoList.add(priceIndicatorEto);

        AddressTo addressTo = new AddressTo("Wrocław", "Wróblewskiego", "27", null, "51-627");

        PriceIndicatorTo priceIndicatorTo = new PriceIndicatorTo(1L, 1L, 10);
        List<PriceIndicatorTo> priceIndicatorToList = new ArrayList<>();
        priceIndicatorToList.add(priceIndicatorTo);

        bookingTo = new BookingTo("Film dla TestCompany", "Film produktowy z dojazdem", 1L, 1L, addressTo,
                DateTimeFormatter.ofPattern( "yyyy-MM-dd" ).format( getCurrentDate(LocalDate.now(),0)),
                DateTimeFormatter.ofPattern( "yyyy-MM-dd" ).format( getCurrentDate(LocalDate.now(),1)),
                priceIndicatorToList);

        AddressEntity addressEntity =  new AddressEntity("Wroclaw", "Wroblewskiego", "27", null, "51-627");
        ServiceEntity serviceEntity = new ServiceEntity("Film produktowy", "Film produktow na bialym tle i odpowiednim oswietleniu", 500D);
        IndicatorEntity indicatorEntity = new IndicatorEntity("Podroz sluzbowa", "Paliwo, amortyzacja", 40);

        bookingEntity = new BookingEntity("Film dla TestCompany", "Film produktowy z dojazdem", 900D,
                addressEntity, new UserEntity(), serviceEntity, false, getCurrentDate(LocalDate.now(),0),
                getCurrentDate(LocalDate.now(),1), getCurrentDate(LocalDate.now(),0));
        PriceIndicatorEntity priceIndicatorEntity = new PriceIndicatorEntity(indicatorEntity, bookingEntity, 400, 10);
        List<PriceIndicatorEntity> priceIndicatorEntities = new ArrayList<>();
        priceIndicatorEntities.add(priceIndicatorEntity);
    }

    public LocalDate getCurrentDate(LocalDate currentDate, int addDays) {
        if(addDays != 0) {
            currentDate = currentDate.plusDays(addDays);
        }
        return currentDate;
    }

    @Test
    @DisplayName("Test createBooking Success")
    void testCreateBookingSuccess() throws EntityAlreadyExistsException {
        //Arrange
        when(bookingDao.save(bookingEntity)).thenReturn(bookingEntity);

        //Act
        Optional<BookingEto> result = ucManageBooking.createBooking(bookingTo);

        // Assert
        Assertions.assertTrue(result.isPresent());
        assertThat(bookingEto).isEqualToComparingFieldByField(result.get());
    }

    @Test
    @DisplayName("Test createBooking EntityDoesNotExistException")
    void testCreateBookingEntityDoesNotExistException() {
        //Arrange
        when(serviceDao.findById(bookingEntity.getService().getId())).thenReturn(Optional.empty());

        //Act Assert
        Assertions.assertThrows(EntityDoesNotExistException.class, () ->
                ucManageBooking.createBooking(bookingTo));
    }

    @Test
    @DisplayName("Test createBooking EntityAlreadyExistsException")
    void testCreateBookingEntityAlreadyExistsException() throws EntityAlreadyExistsException {
        //Arrange
        doThrow(EntityAlreadyExistsException.class)
                .when(bookingValidator).verifyIfBookingNameAlreadyExists(bookingEntity.getName());

        //Act Assert
        Assertions.assertThrows(EntityAlreadyExistsException.class, () ->
                ucManageBooking.createBooking(bookingTo));
    }

    @Test
    @DisplayName("Test updateBooking Success")
    void testUpdateBookingSuccess() throws EntityDoesNotExistException, EntityAlreadyExistsException {
        //Arrange
        when(bookingDao.findById(bookingEntity.getId())).thenReturn(Optional.of(bookingEntity));

        //Act
        Optional<BookingEto> result = ucManageBooking.updateBooking(bookingTo, bookingEntity.getId());

        // Assert
        Assertions.assertTrue(result.isPresent());
        assertThat(bookingEto).isEqualToComparingFieldByField(result.get());
    }

    @Test
    @DisplayName("Test updateBooking EntityAlreadyExistsException")
    void testUpdateBookingEntityAlreadyExistsException() throws EntityAlreadyExistsException {
        //Arrange
        bookingTo.setName("Fotografia dla firmy");
        when(bookingDao.findById(bookingEntity.getId())).thenReturn(Optional.of(bookingEntity));
        doThrow(EntityAlreadyExistsException.class)
                .when(bookingValidator).verifyIfBookingNameAlreadyExists(bookingTo.getName());

        //Act Assert
        Assertions.assertThrows(EntityAlreadyExistsException.class, () ->
                ucManageBooking.updateBooking(bookingTo, bookingEntity.getId()));
    }

    @Test
    @DisplayName("Test updateBooking EntityDoesNotExistException")
    void testUpdateBookingEntityDoesNotExistException() {
        //Arrange
        when(bookingDao.findById(bookingEntity.getId())).thenReturn(Optional.empty());

        //Act Assert
        Assertions.assertThrows(EntityDoesNotExistException.class, () ->
                ucManageBooking.updateBooking(bookingTo, bookingEntity.getId()));
    }
}
