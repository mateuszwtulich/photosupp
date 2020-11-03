package com.wtulich.photosupp.serviceordering.logic.impl;

import com.wtulich.photosupp.general.logic.api.exception.EntityAlreadyExistsException;
import com.wtulich.photosupp.general.logic.api.exception.EntityDoesNotExistException;
import com.wtulich.photosupp.general.logic.api.exception.EntityHasAssignedEntitiesException;
import com.wtulich.photosupp.serviceordering.logic.api.ServiceOrdering;
import com.wtulich.photosupp.serviceordering.logic.api.to.*;
import com.wtulich.photosupp.serviceordering.logic.api.usecase.*;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServiceOrderingImpl implements ServiceOrdering {

    @Inject
    private UcCalculateService ucCalculateService;

    @Inject
    private UcDeleteBooking ucDeleteBooking;

    @Inject
    private UcDeleteIndicator ucDeleteIndicator;

    @Inject
    private UcDeleteService ucDeleteService;

    @Inject
    private UcFindAddress ucFindAddress;

    @Inject
    private UcFindBooking ucFindBooking;

    @Inject
    private UcFindIndicator ucFindIndicator;

    @Inject
    private UcFindService ucFindService;

    @Inject
    private UcManageBooking ucManageBooking;

    @Inject
    private UcManageIndicator ucManageIndicator;

    @Inject
    private UcManageService ucManageService;

    @Override
    public Optional<CalculateCto> calculateService(CalculateTo calculateTo) throws EntityDoesNotExistException {
        return ucCalculateService.calculateService(calculateTo);
    }

    @Override
    public void deleteBooking(Long id) throws EntityDoesNotExistException {
        ucDeleteBooking.deleteBooking(id);
    }

    @Override
    public void deleteIndicator(Long id) throws EntityDoesNotExistException, EntityHasAssignedEntitiesException {
        ucDeleteIndicator.deleteIndicator(id);
    }

    @Override
    public void deleteService(Long id) throws EntityDoesNotExistException, EntityHasAssignedEntitiesException {
        ucDeleteService.deleteService(id);
    }

    @Override
    public Optional<List<String>> findAllCities() {
        return ucFindAddress.findAllCities();
    }

    @Override
    public Optional<List<String>> findAllStreets() {
        return ucFindAddress.findAllStreets();
    }

    @Override
    public Optional<BookingEto> findBooking(Long id) throws EntityDoesNotExistException {
        return ucFindBooking.findBooking(id);
    }

    @Override
    public Optional<List<BookingEto>> findAllBookings() {
        return ucFindBooking.findAllBookings();
    }

    @Override
    public Optional<List<IndicatorEto>> findAllIndicators() {
        return ucFindIndicator.findAllIndicators();
    }

    @Override
    public Optional<List<ServiceEto>> findAllServices() {
        return ucFindService.findAllServices();
    }

    @Override
    public Optional<BookingEto> createBooking(BookingTo bookingTo) throws EntityAlreadyExistsException,
            EntityDoesNotExistException {
        return ucManageBooking.createBooking(bookingTo);
    }

    @Override
    public Optional<BookingEto> updateBooking(BookingTo bookingTo, Long id) throws EntityDoesNotExistException,
            EntityAlreadyExistsException {
        return ucManageBooking.updateBooking(bookingTo, id);
    }

    @Override
    public Optional<IndicatorEto> createIndicator(IndicatorTo indicatorTo) throws EntityAlreadyExistsException {
        return ucManageIndicator.createIndicator(indicatorTo);
    }

    @Override
    public Optional<IndicatorEto> updateIndicator(IndicatorTo indicatorTo, Long id) throws EntityDoesNotExistException,
            EntityAlreadyExistsException {
        return ucManageIndicator.updateIndicator(indicatorTo, id);
    }

    @Override
    public Optional<ServiceEto> createService(ServiceTo serviceTo) throws EntityAlreadyExistsException {
        return ucManageService.createService(serviceTo);
    }

    @Override
    public Optional<ServiceEto> updateService(ServiceTo serviceTo, Long id) throws EntityDoesNotExistException,
            EntityAlreadyExistsException {
        return ucManageService.updateService(serviceTo, id);
    }
}
