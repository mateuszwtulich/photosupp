package com.wtulich.photosupp.orderhandling.logic.impl.usecase;

import com.wtulich.photosupp.general.logic.api.exception.EntityAlreadyExistsException;
import com.wtulich.photosupp.general.logic.api.exception.EntityDoesNotExistException;
import com.wtulich.photosupp.orderhandling.dataaccess.api.dao.OrderDao;
import com.wtulich.photosupp.orderhandling.logic.api.exception.OrderStatusInappropriateException;
import com.wtulich.photosupp.orderhandling.logic.api.mapper.OrderMapper;
import com.wtulich.photosupp.orderhandling.logic.api.to.OrderEto;
import com.wtulich.photosupp.orderhandling.logic.api.to.OrderTo;
import com.wtulich.photosupp.orderhandling.logic.api.usecase.UcManageOrder;
import com.wtulich.photosupp.orderhandling.logic.impl.validator.OrderValidator;
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
import java.util.Optional;

@Validated
@Named
public class UcManageOrderImpl implements UcManageOrder {

    private static final Logger LOG = LoggerFactory.getLogger(UcManageOrderImpl.class);
    private static final String ID_CANNOT_BE_NULL = "id cannot be a null value";
    private static final String CREATE_ORDER_LOG = "Create Order with number {} in database.";
    private static final String UPDATE_ORDER_LOG = "Update Order with id {} from database.";
    private static final String CLOSE_ORDER_LOG = "Close Order with number {} in database.";
    private static final String ACCEPT_ORDER_LOG = "Accept Order with number {} in database.";
    private static final String SEND_TO_VERIFICATION_ORDER_LOG = "Send to verification Order with number {} in database.";

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

    @Inject
    private OrderValidator orderValidator;

    @Override
    public Optional<OrderEto> createOrder(OrderTo orderTo) throws EntityDoesNotExistException, EntityAlreadyExistsException {
        return Optional.empty();
    }

    @Override
    public Optional<OrderEto> updateOrder(OrderTo orderTo, Long id) throws EntityDoesNotExistException, EntityAlreadyExistsException {
        return Optional.empty();
    }

    @Override
    public Optional<OrderEto> finishOrder(Long id) throws EntityDoesNotExistException, OrderStatusInappropriateException {
        return Optional.empty();
    }

    @Override
    public Optional<OrderEto> acceptOrder(Long id) throws EntityDoesNotExistException, OrderStatusInappropriateException {
        return Optional.empty();
    }

    @Override
    public Optional<OrderEto> sendOrderToVerification(Long id) throws EntityDoesNotExistException, OrderStatusInappropriateException {
        return Optional.empty();
    }
}
