package com.wtulich.photosupp.serviceordering.logic.impl.usecase;

import com.wtulich.photosupp.general.logic.api.exception.EntityDoesNotExistException;
import com.wtulich.photosupp.general.security.enums.ApplicationPermissions;
import com.wtulich.photosupp.serviceordering.dataaccess.api.dao.IndicatorDao;
import com.wtulich.photosupp.serviceordering.dataaccess.api.dao.ServiceDao;
import com.wtulich.photosupp.serviceordering.dataaccess.api.entity.IndicatorEntity;
import com.wtulich.photosupp.serviceordering.dataaccess.api.entity.ServiceEntity;
import com.wtulich.photosupp.serviceordering.logic.api.to.*;
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
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class})
@SpringBootTest
@AutoConfigureMockMvc
public class UcCalculateServiceTest {

    @Autowired
    private UcCalculateServiceImpl ucCalculateService;

    @MockBean
    private ServiceDao serviceDao;

    @MockBean
    private IndicatorDao indicatorDao;

    private CalculateCto calculateCto;
    private CalculateTo calculateTo;
    private ServiceEntity serviceEntity;
    private IndicatorEntity indicatorEntity;

    @BeforeEach
    void setUp() {
        AddressEto addressEto = new AddressEto(1L, "Wroclaw", "Wroblewskiego", "27", null, "51-627");
        ServiceEto serviceEto = new ServiceEto(1L, "Film produktowy", "Film produktow na bialym tle i odpowiednim oswietleniu", 500D);
        IndicatorEto indicatorEto = new IndicatorEto(1L, "Podroz sluzbowa", "Paliwo, amortyzacja", 40);
        List<PriceIndicatorEto> priceIndicatorEtoList = new ArrayList<>();

        List<PermissionEto> permissionEtoList = new ArrayList<>();
        permissionEtoList.add(new PermissionEto(1L, ApplicationPermissions.A_CRUD_SUPER, "DESC1"));
        RoleEto roleEto = new RoleEto(1L, "ADMIN", "DESC1", permissionEtoList);
        AccountEto accountEto = new AccountEto(1L, "TEST", "PASS", "TEST@test.com", false);
        UserEto userEto = new UserEto(1L, "NAME1", "SURNAME1", accountEto, roleEto);

        BookingEto bookingEto = new BookingEto(1L, "Film dla TestCompany", "Film produktowy z dojazdem", serviceEto, addressEto, userEto, false, 900D,
                DateTimeFormatter.ofPattern( "yyyy-MM-dd" ).format( getCurrentDate(LocalDate.now(),0)),
                DateTimeFormatter.ofPattern( "yyyy-MM-dd" ).format( getCurrentDate(LocalDate.now(),1)),
                DateTimeFormatter.ofPattern( "yyyy-MM-dd" ).format( getCurrentDate(LocalDate.now(),0)),
                priceIndicatorEtoList);
        PriceIndicatorEto priceIndicatorEto = new PriceIndicatorEto(indicatorEto, bookingEto, 400, 10);
        priceIndicatorEtoList.add(priceIndicatorEto);

        calculateCto = new CalculateCto(serviceEto, addressEto, 900D,
                DateTimeFormatter.ofPattern("yyyy-MM-dd").format(getCurrentDate(LocalDate.now(), 0)),
                DateTimeFormatter.ofPattern("yyyy-MM-dd").format(getCurrentDate(LocalDate.now(), 1)),
                priceIndicatorEtoList);

        PriceIndicatorTo priceIndicatorTo = new PriceIndicatorTo(1L, 1L, 10);
        List<PriceIndicatorTo> priceIndicatorToList = new ArrayList<>();
        priceIndicatorToList.add(priceIndicatorTo);
        AddressTo addressTo = new AddressTo("Wrocław", "Wróblewskiego", "27", null, "51-627");

        calculateTo = new CalculateTo(1L, addressTo,
                DateTimeFormatter.ofPattern("yyyy-MM-dd").format(getCurrentDate(LocalDate.now(), 0)),
                DateTimeFormatter.ofPattern("yyyy-MM-dd").format(getCurrentDate(LocalDate.now(), 1)),
                priceIndicatorToList);

        serviceEntity = new ServiceEntity("Film produktowy", "Film produktow na bialym tle i odpowiednim oswietleniu", 500D);
        indicatorEntity = new IndicatorEntity("Podroz sluzbowa", "Paliwo, amortyzacja", 40);

    }

    public LocalDate getCurrentDate(LocalDate currentDate, int addDays) {
        if(addDays != 0) {
            currentDate = currentDate.plusDays(addDays);
        }
        return currentDate;
    }

    @Test
    @DisplayName("Test calculateService Success")
    void testCalculateServiceSuccess() throws EntityDoesNotExistException {
        //Arrange
        when(serviceDao.findById(calculateTo.getServiceId())).thenReturn(Optional.of(serviceEntity));
        when(indicatorDao.findById(1L)).thenReturn(Optional.of(indicatorEntity));

        //Act
        Optional<CalculateCto> result = ucCalculateService.calculateService(calculateTo);

        // Assert
        Assertions.assertTrue(result.isPresent());
        assertThat(calculateCto).isEqualToComparingFieldByField(result.get());
    }

    @Test
    @DisplayName("Test calculateService EntityDoesNotExistException")
    void testCalculateServiceEntityDoesNotExistException() {
        //Arrange
        when(serviceDao.findById(calculateTo.getServiceId())).thenReturn(Optional.empty());

        //Act Assert
        Assertions.assertThrows(EntityDoesNotExistException.class, () ->
                ucCalculateService.calculateService(calculateTo));
    }

    @Test
    @DisplayName("Test calculateService EntityDoesNotExistException2")
    void testCalculateServiceEntityDoesNotExistException2() {
        //Arrange
        when(serviceDao.findById(calculateTo.getServiceId())).thenReturn(Optional.of(serviceEntity));
        when(indicatorDao.findById(1L)).thenReturn(Optional.empty());

        //Act Assert
        Assertions.assertThrows(EntityDoesNotExistException.class, () ->
                ucCalculateService.calculateService(calculateTo));
    }
}
