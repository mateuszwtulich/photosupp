package com.wtulich.photosupp.orderhandling.logic.impl.usecase;

import com.wtulich.photosupp.general.logic.api.exception.EntityDoesNotExistException;
import com.wtulich.photosupp.orderhandling.dataaccess.api.dao.OrderDao;
import com.wtulich.photosupp.orderhandling.logic.api.mapper.OrderMapper;
import com.wtulich.photosupp.orderhandling.logic.api.to.OrderEto;
import com.wtulich.photosupp.orderhandling.logic.api.usecase.UcFindOrder;
import com.wtulich.photosupp.serviceordering.dataaccess.api.dao.BookingDao;
import com.wtulich.photosupp.serviceordering.logic.api.mapper.AddressMapper;
import com.wtulich.photosupp.serviceordering.logic.api.mapper.BookingMapper;
import com.wtulich.photosupp.serviceordering.logic.api.mapper.IndicatorMapper;
import com.wtulich.photosupp.serviceordering.logic.api.mapper.ServiceMapper;
import com.wtulich.photosupp.userhandling.dataaccess.api.dao.UserDao;
import com.wtulich.photosupp.userhandling.logic.api.mapper.AccountMapper;
import com.wtulich.photosupp.userhandling.logic.api.mapper.PermissionsMapper;
import com.wtulich.photosupp.userhandling.logic.api.mapper.RoleMapper;
import com.wtulich.photosupp.userhandling.logic.api.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Optional;

@Validated
@Named
public class UcFindOrderImpl implements UcFindOrder {

    private static final Logger LOG = LoggerFactory.getLogger(UcFindOrderImpl.class);
    private static final String ID_CANNOT_BE_NULL = "id cannot be a null value";
    private static final String GET_ORDER_LOG = "Get Order with id {} from database.";
    private static final String GET_ALL_ORDERS_LOG = "Get all Orders from database.";

    @Inject
    private OrderDao orderDao;

    @Inject
    private BookingDao bookingDao;

    @Inject
    private UserDao userDao;

    @Inject
    private OrderMapper orderMapper;

    @Inject
    private BookingMapper bookingMapper;

    @Inject
    private ServiceMapper serviceMapper;

    @Inject
    private AddressMapper addressMapper;

    @Inject
    private UserMapper userMapper;

    @Inject
    private IndicatorMapper indicatorMapper;

    @Inject
    private RoleMapper roleMapper;

    @Inject
    private AccountMapper accountMapper;

    @Inject
    private PermissionsMapper permissionsMapper;

    @Override
    public Optional<List<OrderEto>> findAllOrders() {
        return Optional.empty();
    }

    @Override
    public Optional<OrderEto> findOrder(Long id) throws EntityDoesNotExistException {
        return Optional.empty();
    }
}
