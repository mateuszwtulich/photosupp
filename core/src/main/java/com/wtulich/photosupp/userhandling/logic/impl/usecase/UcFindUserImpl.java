package com.wtulich.photosupp.userhandling.logic.impl.usecase;

import com.wtulich.photosupp.userhandling.dataaccess.api.dao.UserDao;
import com.wtulich.photosupp.userhandling.dataaccess.api.entity.UserEntity;
import com.wtulich.photosupp.userhandling.logic.api.mapper.AccountMapper;
import com.wtulich.photosupp.userhandling.logic.api.mapper.RoleMapper;
import com.wtulich.photosupp.userhandling.logic.api.mapper.UserMapper;
import com.wtulich.photosupp.userhandling.logic.api.to.UserEto;
import com.wtulich.photosupp.userhandling.logic.api.usecase.UcFindUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.Optional;

@Validated
@Named
public class UcFindUserImpl implements UcFindUser {

    private static final Logger LOG = LoggerFactory.getLogger(UcFindUserImpl.class);
    private static final String ID_CANNOT_BE_NULL = "id cannot be a null value";

    @Inject
    private UserDao userDao;

    @Inject
    private UserMapper userMapper;

    @Inject
    private AccountMapper accountMapper;

    @Inject
    private RoleMapper roleMapper;

    @Override
    public UserEto findUser(Long id) {

        Objects.requireNonNull(id, ID_CANNOT_BE_NULL);

        LOG.debug("Get User with id {} from database.", id);
        UserEntity userEntity = userDao.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NO_CONTENT, "User with id " + id + " does not exist."));

        return toUserEto(userEntity);
    }

    @Override
    public List<UserEto> findAllUsers() {
        LOG.debug("Get all Users from database.");
        Optional<List<UserEntity>> usersList = Optional.of(userDao.findAll());

        return usersList.orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT)).stream()
                .map(userEntity -> toUserEto(userEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserEto> findAllUsersByRoleId(Long roleId) {

        Objects.requireNonNull(roleId, ID_CANNOT_BE_NULL);
        LOG.debug("Get Users with Role id {} from database.", roleId);

        return userDao.findAllByRole_Id(roleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT)).stream()
                .map(userEntity -> toUserEto(userEntity))
                .collect(Collectors.toList());
    }

    private UserEto toUserEto(UserEntity userEntity){
        UserEto userEto = userMapper.toUserEto(userEntity);
        userEto.setAccountEto(accountMapper.toAccountEto(userEntity.getAccount()));
        userEto.setRoleEto(roleMapper.toRoleEto(userEntity.getRole()));
        return userEto;
    }
}
