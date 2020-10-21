package com.wtulich.photosupp.userhandling.service.impl.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wtulich.photosupp.general.logic.api.exception.EntityAlreadyExistsException;
import com.wtulich.photosupp.general.security.enums.ApplicationPermissions;
import com.wtulich.photosupp.userhandling.logic.api.to.AccountEto;
import com.wtulich.photosupp.userhandling.logic.api.to.AccountTo;
import com.wtulich.photosupp.userhandling.logic.api.to.PermissionEto;
import com.wtulich.photosupp.userhandling.logic.api.to.RoleEto;
import com.wtulich.photosupp.userhandling.logic.api.to.RoleTo;
import com.wtulich.photosupp.userhandling.logic.api.to.UserEto;
import com.wtulich.photosupp.userhandling.logic.api.to.UserTo;
import com.wtulich.photosupp.userhandling.logic.impl.UserHandlingImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class})
@SpringBootTest
@AutoConfigureMockMvc
public class UserRestServiceTests {
    private static String GET_USER_BY_ID_URL = "/user/v1/user/{id}";
    private static String GET_ALL_USERS_URL = "/user/v1/users";
    private static String GET_USERS_BY_ROLE_URL = "/user/v1/users/role/{id}";
    private static String GET_ROLE_BY_ID_URL = "/user/v1/role/{id}";
    private static String GET_ALL_ROLES_URL = "/user/v1/roles";
    private static String GET_ALL_ACCOUNTS_URL = "/user/v1/users/accounts";
    private static String ROLE_ID_URL = "/user/v1/role/{id}";
    private static String USER_ID_URL = "/user/v1/user/{id}";
    private static String ROLE_URL = "/user/v1/role";
    private static String USER_URL = "/user/v1/user";

    @MockBean
    private UserHandlingImpl userHandling;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;
    private RoleEto roleEto;
    private AccountEto accountEto;
    private UserEto userEto;
    private List<PermissionEto> permissionEtoList;
    private RoleTo roleTo;
    private AccountTo accountTo;
    private UserTo userTo;
    private List<Long> permissionsIds;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();

        permissionEtoList = new ArrayList<>();
        permissionEtoList.add(new PermissionEto(1L, ApplicationPermissions.A_CRUD_SUPER, "DESC1"));
        roleEto = new RoleEto(1L, "ADMIN", "DESC1", permissionEtoList);
        accountEto = new AccountEto(1L, "TEST", "PASS", "TEST@test.com", false);
        userEto = new UserEto(1L, "NAME1", "SURNAME1", accountEto, roleEto);

        permissionsIds = new ArrayList<>();
        permissionsIds.add(1L);
        roleTo = new RoleTo("ADMIN", "DESC1", permissionsIds);
        accountTo = new AccountTo("PASS", "TEST@test.com");
        userTo = new UserTo("NAME1", "SURNAME1", accountTo, 1L);

//        mockMvc = MockMvcBuilders.standaloneSetup(new UserRestServiceImpl(userHandling)).build();

//        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("GET /user/v1/user/1 - Found")
    void testGetUserByIdFound() throws Exception {
        //Arrange
        when(userHandling.findUser(userEto.getId())).thenReturn(Optional.of(userEto));

        //Act
        MvcResult result = mockMvc.perform(get(GET_USER_BY_ID_URL, userEto.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE))

                //Assert
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertThat(objectMapper.readValue(result.getResponse().getContentAsString(), UserEto.class))
                .isEqualToComparingFieldByField(userEto);
    }

    @Test
    @DisplayName("GET /user/v1/user/1 - No content")
    void testGetUserByIdNoContent() throws Exception {
        //Arrange
        when(userHandling.findUser(userEto.getId()))
                .thenReturn(Optional.ofNullable(null));

        //Act
        MvcResult result = mockMvc.perform(get(GET_USER_BY_ID_URL, userEto.getId()))

                //Assert
                .andExpect(status().isNoContent())
                .andReturn();

        assertThat(result.getResponse().getErrorMessage()).isEqualTo("User with id " + userEto.getId() + " does not exist.");
    }

    @Test
    @DisplayName("GET /user/v1/users - Found")
    void testGetAllUsersFound() throws Exception {
        //Arrange
        ArrayList<UserEto> users = new ArrayList<>();
        users.add(userEto);
        when(userHandling.findAllUsers()).thenReturn(Optional.of(users));

        //Act
        MvcResult result = mockMvc.perform(get(GET_ALL_USERS_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE))

                //Assert
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertThat(objectMapper.readValue(result.getResponse().getContentAsString(), UserEto[].class))
                .isEqualTo(users.toArray());
    }

    @Test
    @DisplayName("GET /user/v1/users - No content")
    void testGetAllUsersNoContent() throws Exception {
        //Arrange
        when(userHandling.findAllUsers()).thenReturn(Optional.ofNullable(null));

        //Act
        MvcResult result = mockMvc.perform(get(GET_ALL_USERS_URL))

                //Assert
                .andExpect(status().isNoContent())
                .andReturn();

        assertThat(result.getResponse().getErrorMessage()).isEqualTo("Users do not exist.");
    }

    @Test
    @DisplayName("GET /user/v1/users/role/{id} - Found")
    void testGetUsersByRoleFound() throws Exception {
        //Arrange
        ArrayList<UserEto> users = new ArrayList<>();
        users.add(userEto);
        when(userHandling.findAllUsersByRoleId(roleEto.getId())).thenReturn(Optional.of(users));

        //Act
        MvcResult result = mockMvc.perform(get(GET_USERS_BY_ROLE_URL, roleEto.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE))

                //Assert
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertThat(objectMapper.readValue(result.getResponse().getContentAsString(), UserEto[].class))
                .isEqualTo(users.toArray());
    }

    @Test
    @DisplayName("GET /user/v1/users/role/{id} - No content")
    void testGetUsersByRoleNoContent() throws Exception {
        //Arrange
        when(userHandling.findAllUsersByRoleId(roleEto.getId())).thenReturn(Optional.ofNullable(null));

        //Act
        MvcResult result = mockMvc.perform(get(GET_USERS_BY_ROLE_URL, roleEto.getId()))

                //Assert
                .andExpect(status().isNoContent())
                .andReturn();

        assertThat(result.getResponse().getErrorMessage()).isEqualTo("Users with role id" + roleEto.getId() + " do not exist.");
    }

    @Test
    @DisplayName("GET /user/v1/role/1 - Found")
    void testGetRoleByIdFound() throws Exception {
        //Arrange
        when(userHandling.findRole(roleEto.getId())).thenReturn(Optional.of(roleEto));

        //Act
        MvcResult result = mockMvc.perform(get(GET_ROLE_BY_ID_URL, roleEto.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE))

                //Assert
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertThat(objectMapper.readValue(result.getResponse().getContentAsString(), RoleEto.class))
                .isEqualToComparingFieldByField(roleEto);
    }

    @Test
    @DisplayName("GET /user/v1/role/1 - No content")
    void testGetRoleByIdNoContent() throws Exception {
        //Arrange
        when(userHandling.findUser(roleEto.getId()))
                .thenReturn(Optional.ofNullable(null));

        //Act
        MvcResult result = mockMvc.perform(get(GET_ROLE_BY_ID_URL, userEto.getId()))

                //Assert
                .andExpect(status().isNoContent())
                .andReturn();

        assertThat(result.getResponse().getErrorMessage()).isEqualTo("Role with id " + roleEto.getId() + " does not exist.");
    }

    @Test
    @DisplayName("GET /user/v1/roles - Found")
    void testGetAllRolesFound() throws Exception {
        //Arrange
        ArrayList<RoleEto> roles = new ArrayList<>();
        roles.add(roleEto);
        when(userHandling.findAllRoles()).thenReturn(Optional.of(roles));

        //Act
        MvcResult result = mockMvc.perform(get(GET_ALL_ROLES_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE))

                //Assert
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertThat(objectMapper.readValue(result.getResponse().getContentAsString(), RoleEto[].class))
                .isEqualTo(roles.toArray());
    }

    @Test
    @DisplayName("GET /user/v1/roles - No content")
    void testGetAllRolesNoContent() throws Exception {
        //Arrange
        when(userHandling.findAllUsers()).thenReturn(Optional.ofNullable(null));

        //Act
        MvcResult result = mockMvc.perform(get(GET_ALL_ROLES_URL))

                //Assert
                .andExpect(status().isNoContent())
                .andReturn();

        assertThat(result.getResponse().getErrorMessage()).isEqualTo("Roles do not exist.");
    }

    @Test
    @DisplayName("GET /user/v1/users/accounts - Found")
    void testGetAllAccountsFound() throws Exception {
        //Arrange
        ArrayList<AccountEto> accounts = new ArrayList<>();
        accounts.add(accountEto);
        when(userHandling.findAllAccounts()).thenReturn(Optional.of(accounts));

        //Act
        MvcResult result = mockMvc.perform(get(GET_ALL_ACCOUNTS_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE))

                //Assert
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertThat(objectMapper.readValue(result.getResponse().getContentAsString(), AccountEto[].class))
                .isEqualTo(accounts.toArray());
    }

    @Test
    @DisplayName("GET /user/v1/users/accounts - No content")
    void testGetAllAccountsNoContent() throws Exception {
        //Arrange
        when(userHandling.findAllAccounts()).thenReturn(Optional.ofNullable(null));

        //Act
        MvcResult result = mockMvc.perform(get(GET_ALL_ACCOUNTS_URL))

                //Assert
                .andExpect(status().isNoContent())
                .andReturn();

        assertThat(result.getResponse().getErrorMessage()).isEqualTo("Accounts do not exist.");
    }

    @Test
    @DisplayName("DELETE /user/v1/role/{id} - OK")
    void testDeleteRoleOk() throws Exception {
        //Act
        mockMvc.perform(delete(ROLE_ID_URL, roleEto.getId()))

                //Assert
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /user/v1/user/{id} - OK")
    void testDeleteUserOk() throws Exception {
        //Act
        mockMvc.perform(delete(USER_ID_URL, userEto.getId()))

                //Assert
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /user/v1/role - OK")
    void testCreateRoleOk() throws Exception {
        //Arrange
        when(userHandling.createRole(roleTo)).thenReturn(Optional.of(roleEto));

        //Act
        MvcResult result = mockMvc.perform(post(ROLE_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(roleTo)))

                //Assert
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertThat(objectMapper.readValue(result.getResponse().getContentAsString(), RoleEto.class))
                .isEqualToComparingFieldByField(roleEto);
    }

    @Test
    @DisplayName("POST /user/v1/role - Not Found")
    void testCreateRoleNotFound() throws Exception {
        //Arrange
        when(userHandling.createRole(roleTo)).thenReturn(Optional.ofNullable(null));

        //Act
        mockMvc.perform(post(ROLE_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(roleTo)))

                //Assert
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    @DisplayName("POST /user/v1/role - Unprocessable Entity")
    void testCreateRoleUnprocessableEntity() throws Exception {
        //Arrange
        when(userHandling.createRole(roleTo)).thenThrow(EntityAlreadyExistsException.class);

        //Act
        mockMvc.perform(post(ROLE_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(roleTo)))

                //Assert
                .andExpect(status().isUnprocessableEntity())
                .andReturn();
    }

//    @Test
//    @DisplayName("POST /user/v1/user - OK")
//    void testCreateUserOk() throws Exception {
//        //Arrange
//        when(userHandling.createUserAndAccountEntities(userTo, null, null)).thenReturn(Optional.of(userEto));
//
//        //Act
//        MvcResult result = mockMvc.perform(post(USER_URL)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(objectMapper.writeValueAsString(userTo)))
//
//                //Assert
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andReturn();
//
//        assertThat(objectMapper.readValue(result.getResponse().getContentAsString(), UserEto.class))
//                .isEqualToComparingFieldByField(userEto);
//    }
//
//    @Test
//    @DisplayName("POST /user/v1/user - Not Found")
//    void testCreateUserNotFound() throws Exception {
//        //Arrange
//        when(userHandling.createUserAndAccountEntities(anyObject(), anyObject(), anyObject())).thenReturn(Optional.ofNullable(null));
//
//        //Act
//        mockMvc.perform(post(USER_URL)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(anyString())
//                .param("request", anyString())
//                .param("errors", anyString()))
//
//                //Assert
//                .andExpect(status().isNotFound())
//                .andReturn();
//    }
//
//    @Test
//    @DisplayName("POST /user/v1/user - Unprocessable Entity")
//    void testCreateUserUnprocessableEntity() throws Exception {
//        //Arrange
//        when(userHandling.createUserAndAccountEntities(userTo, null, null)).thenThrow(EntityAlreadyExistsException.class);
//
//        //Act
//        mockMvc.perform(post(USER_URL)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(objectMapper.writeValueAsString(userTo)))
//
//                //Assert
//                .andExpect(status().isUnprocessableEntity())
//                .andReturn();
//    }
}
