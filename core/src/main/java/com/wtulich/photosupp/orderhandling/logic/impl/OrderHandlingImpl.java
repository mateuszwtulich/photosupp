package com.wtulich.photosupp.orderhandling.logic.impl;

import com.wtulich.photosupp.general.logic.api.exception.EntityAlreadyExistsException;
import com.wtulich.photosupp.general.logic.api.exception.EntityDoesNotExistException;
import com.wtulich.photosupp.general.logic.api.exception.EntityHasAssignedEntitiesException;
import com.wtulich.photosupp.orderhandling.logic.api.OrderHandling;
import com.wtulich.photosupp.orderhandling.logic.api.exception.OrderStatusInappropriateException;
import com.wtulich.photosupp.orderhandling.logic.api.to.*;
import com.wtulich.photosupp.orderhandling.logic.api.usecase.*;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderHandlingImpl implements OrderHandling {

    @Inject
    private UcDeleteComment ucDeleteComment;

    @Inject
    private UcDeleteMediaContent ucDeleteMediaContent;

    @Inject
    private UcDeleteOrder ucDeleteOrder;

    @Inject
    private UcFindComment ucFindComment;

    @Inject
    private UcFindMediaContent ucFindMediaContent;

    @Inject
    private UcFindOrder ucFindOrder;

    @Inject
    private UcManageComment ucManageComment;

    @Inject
    private UcManageMediaContent ucManageMediaContent;

    @Inject
    private UcManageOrder ucManageOrder;

    @Override
    public void deleteComment(Long id) throws EntityDoesNotExistException {

    }

    @Override
    public void deleteMediaContent(Long id) throws EntityDoesNotExistException {

    }

    @Override
    public void deleteAllMediaContent(Long id) throws EntityDoesNotExistException {

    }

    @Override
    public void deleteOrder(Long id) throws EntityDoesNotExistException, EntityHasAssignedEntitiesException {

    }

    @Override
    public Optional<List<CommentEto>> findAllCommentsByOrderId(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<MediaContentEto>> findAllMediaContentByOrderId(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<OrderEto>> findAllOrders() {
        return Optional.empty();
    }

    @Override
    public Optional<OrderEto> findOrder(Long id) throws EntityDoesNotExistException {
        return Optional.empty();
    }

    @Override
    public Optional<MediaContentEto> addMediaContent(MediaContentTo mediaContentTo) throws EntityDoesNotExistException {
        return Optional.empty();
    }

    @Override
    public Optional<MediaContentEto> updateMediaContent(MediaContentTo mediaContentTo, Long id) throws EntityDoesNotExistException {
        return Optional.empty();
    }

    @Override
    public Optional<OrderEto> createOrder(OrderTo orderTo) throws EntityDoesNotExistException, EntityAlreadyExistsException {
        return Optional.empty();
    }

    @Override
    public Optional<OrderEto> updateOrder(OrderTo orderTo, Long id) throws EntityDoesNotExistException, EntityAlreadyExistsException {
        return Optional.empty();
    }

    @Override
    public Optional<OrderEto> closeOrder(Long id) throws EntityDoesNotExistException, OrderStatusInappropriateException {
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
