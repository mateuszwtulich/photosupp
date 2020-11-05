package com.wtulich.photosupp.serviceordering.dataaccess.api;

import com.wtulich.photosupp.config.H2JpaConfig;
import com.wtulich.photosupp.general.security.enums.ApplicationPermissions;
import com.wtulich.photosupp.serviceordering.dataaccess.api.dao.BookingDao;
import com.wtulich.photosupp.serviceordering.dataaccess.api.entity.*;
import com.wtulich.photosupp.userhandling.dataaccess.api.entity.AccountEntity;
import com.wtulich.photosupp.userhandling.dataaccess.api.entity.PermissionEntity;
import com.wtulich.photosupp.userhandling.dataaccess.api.entity.RoleEntity;
import com.wtulich.photosupp.userhandling.dataaccess.api.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({SpringExtension.class})
@SpringBootTest(classes = {H2JpaConfig.class})
public class BookingDaoTest {

    @Autowired
    private BookingDao bookingDao;

    @Test
    void existsByName_TRUE() {
        //Act Assert
        Assertions.assertEquals(true, bookingDao.existsByName("Film dla TestCompany"));
    }

    @Test
    void existsByName_FALSE() {
        //Act Assert
        Assertions.assertEquals(false, bookingDao.existsByName("Film dla TestCompany!"));
    }

    @Test
    void findAllByService_Id_TRUE() {
        //Arrange
        AccountEntity accountEntity = new AccountEntity("user1", "passw0rd", "user1@test.com", false);
        accountEntity.setId(1L);

        PermissionEntity permissionEntity = new PermissionEntity(ApplicationPermissions.AUTH_USER, "Standard user with no special permissions.");
        permissionEntity.setId(6L);

        List<PermissionEntity> permissionEntityList = new ArrayList<>();
        permissionEntityList.add(permissionEntity);

        RoleEntity roleEntity = new RoleEntity("USER", "Standard user with no special permissions", permissionEntityList);
        roleEntity.setId(2L);

        UserEntity userEntity = new UserEntity("NAME", "SURNAME", roleEntity, accountEntity);
        userEntity.setId(1L);

        AddressEntity addressEntity =  new AddressEntity("Wroclaw", "Wroblewskiego", "27", null, "51-627");
        addressEntity.setId(1L);
        ServiceEntity serviceEntity = new ServiceEntity("Film produktowy", "Film produktow na bialym tle i odpowiednim oswietleniu", 500D);
        serviceEntity.setId(1L);
        IndicatorEntity indicatorEntity = new IndicatorEntity("Podroz sluzbowa", "Paliwo, amortyzacja", 40);
        indicatorEntity.setId(1L);

        BookingEntity bookingEntity = new BookingEntity("Film dla TestCompany", "Film produktowy z dojazdem", 900D,
                addressEntity, userEntity, serviceEntity, false, LocalDate.now(), LocalDate.now(), LocalDate.now());
        bookingEntity.setId(1L);

        //Act
        List<BookingEntity> bookingEntities = bookingDao.findAllByService_Id(1L);

        // Assert
        assertThat(bookingEntities).hasSize(1);
        assertThat(bookingEntities.get(0)).isEqualToIgnoringGivenFields(bookingEntity, "user", "priceIndicatorList", "start", "end", "modificationDate");
        assertThat(bookingEntities.get(0).getAddress()).isEqualToIgnoringGivenFields(bookingEntity.getAddress(), "bookingList");
        assertThat(bookingEntities.get(0).getService()).isEqualToIgnoringGivenFields(bookingEntity.getService(), "bookingList");
        assertThat(bookingEntities.get(0).getUser()).isEqualToIgnoringGivenFields(bookingEntity.getUser(), "orderList", "bookingList", "role", "account");
    }

    @Test
    void findAllByService_Id_FALSE() {
        //Act
        List<BookingEntity> bookingEntities = bookingDao.findAllByService_Id(2L);

        // Assert
        assertThat(bookingEntities).hasSize(0);
    }

    @Test
    void findAllByUser_Id_TRUE() {
        //Arrange
        AccountEntity accountEntity = new AccountEntity("user1", "passw0rd", "user1@test.com", false);
        accountEntity.setId(1L);

        PermissionEntity permissionEntity = new PermissionEntity(ApplicationPermissions.AUTH_USER, "Standard user with no special permissions.");
        permissionEntity.setId(6L);

        List<PermissionEntity> permissionEntityList = new ArrayList<>();
        permissionEntityList.add(permissionEntity);

        RoleEntity roleEntity = new RoleEntity("USER", "Standard user with no special permissions", permissionEntityList);
        roleEntity.setId(2L);

        UserEntity userEntity = new UserEntity("NAME", "SURNAME", roleEntity, accountEntity);
        userEntity.setId(1L);

        AddressEntity addressEntity =  new AddressEntity("Wroclaw", "Wroblewskiego", "27", null, "51-627");
        addressEntity.setId(1L);
        ServiceEntity serviceEntity = new ServiceEntity("Film produktowy", "Film produktow na bialym tle i odpowiednim oswietleniu", 500D);
        serviceEntity.setId(1L);
        IndicatorEntity indicatorEntity = new IndicatorEntity("Podroz sluzbowa", "Paliwo, amortyzacja", 40);
        indicatorEntity.setId(1L);

        BookingEntity bookingEntity = new BookingEntity("Film dla TestCompany", "Film produktowy z dojazdem", 900D,
                addressEntity, userEntity, serviceEntity, false, LocalDate.now(), LocalDate.now(), LocalDate.now());
        bookingEntity.setId(1L);

        //Act
        List<BookingEntity> bookingEntities = bookingDao.findAllByUser_Id(1L);

        // Assert
        assertThat(bookingEntities).hasSize(1);
        assertThat(bookingEntities.get(0)).isEqualToIgnoringGivenFields(bookingEntity, "user", "priceIndicatorList", "start", "end", "modificationDate");
        assertThat(bookingEntities.get(0).getAddress()).isEqualToIgnoringGivenFields(bookingEntity.getAddress(), "bookingList");
        assertThat(bookingEntities.get(0).getService()).isEqualToIgnoringGivenFields(bookingEntity.getService(), "bookingList");
        assertThat(bookingEntities.get(0).getUser()).isEqualToIgnoringGivenFields(bookingEntity.getUser(), "orderList", "bookingList", "role", "account");
    }

    @Test
    void findAllByUser_Id_FALSE() {
        //Act
        List<BookingEntity> bookingEntities = bookingDao.findAllByUser_Id(2L);

        // Assert
        assertThat(bookingEntities).hasSize(0);
    }
}
