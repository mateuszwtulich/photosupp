package com.wtulich.photosupp.orderhandling.logic.impl.usecase;

import com.wtulich.photosupp.general.logic.api.exception.EntityDoesNotExistException;
import com.wtulich.photosupp.general.security.enums.ApplicationPermissions;
import com.wtulich.photosupp.general.utils.enums.OrderStatus;
import com.wtulich.photosupp.orderhandling.dataaccess.api.dao.CommentDao;
import com.wtulich.photosupp.orderhandling.dataaccess.api.dao.OrderDao;
import com.wtulich.photosupp.orderhandling.dataaccess.api.entity.CommentEntity;
import com.wtulich.photosupp.orderhandling.dataaccess.api.entity.OrderEntity;
import com.wtulich.photosupp.orderhandling.logic.api.to.CommentEto;
import com.wtulich.photosupp.orderhandling.logic.api.to.CommentTo;
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
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class})
@SpringBootTest
@AutoConfigureMockMvc
public class UcManageCommentTest {

    @Autowired
    private UcManageCommentImpl ucManageComment;

    @MockBean
    private CommentDao commentDao;

    @MockBean
    private OrderDao orderDao;

    private CommentEntity commentEntity;
    private CommentEto commentEto;
    private CommentTo commentTo;
    private OrderEntity orderEntity;

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

        UserEntity userEntity = new UserEntity("NAME", "SURNAME", roleEntity, accountEntity);
        userEntity.setId(1L);

        List<PermissionEntity> permissionEntities2 = new ArrayList<>();
        PermissionEntity permissionEntity2 = new PermissionEntity(ApplicationPermissions.AUTH_USER, "Standard user with no special permissions.");
        permissionEntity2.setId(6L);
        permissionEntities2.add(permissionEntity2);

        RoleEntity roleEntity2 = new RoleEntity( "USER", "Standard user with no special permissions", permissionEntities2);
        roleEntity2.setId(2L);
        AccountEntity accountEntity2 = new AccountEntity( "user2", "passw0rd", "user2@test.com", true);
        accountEntity2.setId(2L);

        UserEntity userEntity2 = new UserEntity("NAME2", "SURNAME2", roleEntity2, accountEntity2);
        userEntity2.setId(2L);

        orderEntity = new OrderEntity("INVIU_00001", OrderStatus.IN_PROGRESS, 1000D, LocalDate.now(), userEntity2, userEntity,  null );
        orderEntity.setId(1L);

        commentEntity = new CommentEntity("Perfect, thanks!", userEntity, orderEntity, LocalDate.now());

        List<PermissionEto> permissionEtoList2 = new ArrayList<>();
        permissionEtoList2.add(new PermissionEto(6L, ApplicationPermissions.AUTH_USER, "Standard user with no special permissions."));
        RoleEto roleEto2 = new RoleEto(2L, "USER", "Standard user with no special permissions", permissionEtoList2);
        AccountEto accountEto2 = new AccountEto(2L, "user2", "passw0rd", "user2@test.com", true);
        UserEto userEto2 = new UserEto(2L, "NAME2", "SURNAME2", accountEto2, roleEto2);

        commentEto = new CommentEto(1L, "Perfect, thanks!", 1L, userEto2,
                DateTimeFormatter.ofPattern( "yyyy-MM-dd" ).format(LocalDate.now()));

        commentTo = new CommentTo("Perfect, thanks!", 1L, 2L);
    }

    @Test
    @DisplayName("Test createComment Success")
    void testCreateCommentSuccess() throws EntityDoesNotExistException {
        //Arrange
        when(orderDao.findById(orderEntity.getId())).thenReturn(Optional.ofNullable(orderEntity));
        when(commentDao.save(commentEntity)).thenReturn(commentEntity);

        //Act
        Optional<CommentEto> result = ucManageComment.createComment(commentTo);

        // Assert
        Assertions.assertTrue(result.isPresent());
        assertThat(commentEto).isEqualToComparingFieldByField(result.get());
    }

    @Test
    @DisplayName("Test createComment EntityDoesNotExistException")
    void testCreateCommentEntityDoesNotExistException() throws EntityDoesNotExistException {
        //Arrange
        when(orderDao.findById(orderEntity.getId())).thenReturn(Optional.ofNullable(null));

        //Act Assert
        Assertions.assertThrows(EntityDoesNotExistException.class, () ->
                ucManageComment.createComment(commentTo));
    }

    @Test
    @DisplayName("Test updateComment Success")
    void testUpdateCommentSuccess() throws EntityDoesNotExistException {
        //Arrange
        commentTo.setContent("Updated");
        commentEto.setContent("Updated");
        when(commentDao.findById(commentEntity.getId())).thenReturn(Optional.ofNullable(commentEntity));

        //Act
        Optional<CommentEto> result = ucManageComment.updateComment(commentTo, commentEntity.getId());

        // Assert
        Assertions.assertTrue(result.isPresent());
        assertThat(commentEto).isEqualToComparingFieldByField(result.get());
    }

    @Test
    @DisplayName("Test updateComment EntityDoesNotExistException")
    void testUpdateCommentEntityDoesNotExistException() {
        //Arrange
        when(commentDao.findById(commentEntity.getId())).thenReturn(Optional.empty());

        //Act Assert
        Assertions.assertThrows(EntityDoesNotExistException.class, () ->
                ucManageComment.updateComment(commentTo, commentEntity.getId()));
    }
}
