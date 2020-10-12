package com.wtulich.photosupp.userhandling.logic.impl.usecase;

import com.wtulich.photosupp.userhandling.dataaccess.api.dao.UserDao;
import com.wtulich.photosupp.userhandling.dataaccess.api.entity.UserEntity;
import com.wtulich.photosupp.userhandling.logic.api.mapper.UserMapper;
import com.wtulich.photosupp.userhandling.logic.api.to.UserEto;
import com.wtulich.photosupp.userhandling.logic.api.usecase.UcFindUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Named
@Validated
public class UcFindUserImpl implements UcFindUser {

    private static final Logger LOG = LoggerFactory.getLogger(UcFindUserImpl.class);
    private static final String ID_CANNOT_BE_NULL = "id cannot be a null value";

    @Inject
    private UserDao userDao;

    @Inject
    private UserMapper userMapper;

    @Override
    public UserEto findUser(Long id) {

        Objects.requireNonNull(id, ID_CANNOT_BE_NULL);

        LOG.debug("Get User with id {} from database.", id);
        UserEntity userEntity = userDao.findById(id).get();
        return userMapper.toUserEto(userEntity);
    }

    @Override
    public List<UserEto> findAllUsers() {
        LOG.debug("Get all Users from database.");
        return userDao.findAll().stream()
                .map(userEntity -> userMapper.toUserEto(userEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserEto> findAllUsersByRoleId(Long roleId) {

        Objects.requireNonNull(roleId, ID_CANNOT_BE_NULL);

        LOG.debug("Get Users with Role id {} from database.", roleId);
        return userDao.findAllByRole_Id(roleId).stream()
                .map(userEntity -> userMapper.toUserEto(userEntity))
                .collect(Collectors.toList());
    }
}
