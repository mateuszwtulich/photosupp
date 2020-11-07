package com.wtulich.photosupp.orderhandling.logic.impl.usecase;

import com.wtulich.photosupp.general.logic.api.exception.EntityAlreadyExistsException;
import com.wtulich.photosupp.general.logic.api.exception.EntityDoesNotExistException;
import com.wtulich.photosupp.general.security.enums.ApplicationPermissions;
import com.wtulich.photosupp.general.utils.enums.OrderStatus;
import com.wtulich.photosupp.orderhandling.dataaccess.api.dao.CommentDao;
import com.wtulich.photosupp.orderhandling.dataaccess.api.dao.OrderDao;
import com.wtulich.photosupp.orderhandling.dataaccess.api.entity.CommentEntity;
import com.wtulich.photosupp.orderhandling.dataaccess.api.entity.OrderEntity;
import com.wtulich.photosupp.orderhandling.logic.api.to.CommentEto;
import com.wtulich.photosupp.orderhandling.logic.api.to.CommentTo;
import com.wtulich.photosupp.orderhandling.logic.api.to.OrderEto;
import com.wtulich.photosupp.orderhandling.logic.api.to.OrderTo;
import com.wtulich.photosupp.orderhandling.logic.impl.validator.OrderValidator;
import com.wtulich.photosupp.userhandling.dataaccess.api.dao.UserDao;
import com.wtulich.photosupp.userhandling.dataaccess.api.entity.AccountEntity;
import com.wtulich.photosupp.userhandling.dataaccess.api.entity.PermissionEntity;
import com.wtulich.photosupp.userhandling.dataaccess.api.entity.RoleEntity;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class})
@SpringBootTest
@AutoConfigureMockMvc
public class UcManageOrderTest {

    @Autowired
    private UcManageOrderImpl ucManageOrder;

    @MockBean
    private OrderDao orderDao;

    @MockBean
    private UserDao userDao;

    @MockBean
    private OrderValidator orderValidator;

    private OrderEntity orderEntity;
    private OrderEto orderEto;
    private OrderTo orderTo;
    private UserEntity userEntity;
    private UserEntity userEntity2;

    @BeforeEach
    void setUp() {
        List<PermissionEntity> permissionEntities = new ArrayList<>();
        PermissionEntity permissionEntity = new PermissionEntity(ApplicationPermissions.AUTH_USER, "User has possibility to use CRUD operations one every functionality.");
        permissionEntity.setId(1L);
        permissionEntities.add(permissionEntity);

        RoleEntity roleEntity = new RoleEntity( "MANAGER", "Manager with all permissions in order management", permissionEntities);
        roleEntity.setId(1L);
        AccountEntity accountEntity = new AccountEntity( "user1", "passw0rd", "user1@test.com", true);
        accountEntity.setId(1L);

        userEntity = new UserEntity("NAME", "SURNAME", roleEntity, accountEntity);
        userEntity.setId(1L);

        List<PermissionEntity> permissionEntities2 = new ArrayList<>();
        PermissionEntity permissionEntity2 = new PermissionEntity(ApplicationPermissions.AUTH_USER, "Standard user with no special permissions.");
        permissionEntity2.setId(6L);
        permissionEntities2.add(permissionEntity2);

        RoleEntity roleEntity2 = new RoleEntity( "USER", "Standard user with no special permissions", permissionEntities2);
        roleEntity2.setId(2L);
        AccountEntity accountEntity2 = new AccountEntity( "user2", "passw0rd", "user2@test.com", true);
        accountEntity2.setId(2L);

        userEntity2 = new UserEntity("NAME2", "SURNAME2", roleEntity2, accountEntity2);
        userEntity2.setId(2L);

        orderEntity = new OrderEntity("INVIU_00001", OrderStatus.IN_PROGRESS, 1000D, LocalDate.now(), userEntity2, userEntity,  null );
        orderEntity.setId(1L);

        List<PermissionEto> permissionEtoList = new ArrayList<>();
        permissionEtoList.add(new PermissionEto(1L, ApplicationPermissions.A_CRUD_SUPER, "DESC1"));
        RoleEto roleEto = new RoleEto(1L, "MANAGER", "Manager with all permissions in order management", permissionEtoList);
        AccountEto accountEto = new AccountEto(1L, "user1", "passw0rd", "user1@test.com", false);
        UserEto userEto = new UserEto(1L, "NAME", "SURNAME", accountEto, roleEto);

        List<PermissionEto> permissionEtoList2 = new ArrayList<>();
        permissionEtoList2.add(new PermissionEto(6L, ApplicationPermissions.AUTH_USER, "Standard user with no special permissions."));
        RoleEto roleEto2 = new RoleEto(2L, "USER", "Standard user with no special permissions", permissionEtoList2);
        AccountEto accountEto2 = new AccountEto(2L, "user2", "passw0rd", "user2@test.com", true);
        UserEto userEto2 = new UserEto(2L, "NAME2", "SURNAME2", accountEto2, roleEto2);

        orderEto = new OrderEto(1L, "INVIU_00001", userEto, userEto2, OrderStatus.IN_PROGRESS, null, 1000D,
                DateTimeFormatter.ofPattern( "yyyy-MM-dd" ).format(LocalDate.now()));

        orderTo = new OrderTo(1L, 2L, 1L, 1000D);
    }

    @Test
    @DisplayName("Test createOrder Success")
    void testCreateCommentSuccess() throws EntityDoesNotExistException, EntityAlreadyExistsException {
        //Arrange
        when(userDao.findById(userEntity.getId())).thenReturn(Optional.ofNullable(userEntity));
        when(userDao.findById(userEntity2.getId())).thenReturn(Optional.ofNullable(userEntity2));
        when(orderDao.save(orderEntity)).thenReturn(orderEntity);

        //Act
        Optional<OrderEto> result = ucManageOrder.createOrder(orderTo);

        // Assert
        Assertions.assertTrue(result.isPresent());
        assertThat(orderEto).isEqualToComparingFieldByField(result.get());
    }

    @Test
    @DisplayName("Test createOrder EntityDoesNotExistException")
    void testCreateCommentEntityDoesNotExistException() {
        //Arrange
        when(userDao.findById(userEntity.getId())).thenReturn(Optional.ofNullable(null));

        //Act Assert
        Assertions.assertThrows(EntityDoesNotExistException.class, () ->
                ucManageOrder.createOrder(orderTo));
    }

    @Test
    @DisplayName("Test createOrder EntityAlreadyExistsException")
    void testCreateCommentEntityAlreadyExistsException() throws EntityAlreadyExistsException {
        //Arrange
        doThrow(EntityAlreadyExistsException.class)
                .when(orderValidator).verifyIfBookingHasAssignedOrders(any());

        //Act Assert
        Assertions.assertThrows(EntityAlreadyExistsException.class, () ->
                ucManageOrder.createOrder(orderTo));
    }

//    @Test
//    @DisplayName("Test updateComment Success")
//    void testUpdateCommentSuccess() throws EntityDoesNotExistException {
//        //Arrange
//        commentTo.setContent("Updated");
//        commentEto.setContent("Updated");
//        when(commentDao.findById(commentEntity.getId())).thenReturn(Optional.ofNullable(commentEntity));
//
//        //Act
//        Optional<CommentEto> result = ucManageComment.updateComment(commentTo, commentEntity.getId());
//
//        // Assert
//        Assertions.assertTrue(result.isPresent());
//        assertThat(commentEto).isEqualToComparingFieldByField(result.get());
//    }
//
//    @Test
//    @DisplayName("Test updateComment EntityDoesNotExistException")
//    void testUpdateCommentEntityDoesNotExistException() {
//        //Arrange
//        when(commentDao.findById(commentEntity.getId())).thenReturn(Optional.empty());
//
//        //Act Assert
//        Assertions.assertThrows(EntityDoesNotExistException.class, () ->
//                ucManageComment.updateComment(commentTo, commentEntity.getId()));
//    }
}
