package com.wtulich.photosupp.orderhandling.logic.impl.usecase;

import com.wtulich.photosupp.general.logic.api.exception.EntityDoesNotExistException;
import com.wtulich.photosupp.orderhandling.dataaccess.api.dao.MediaContentDao;
import com.wtulich.photosupp.orderhandling.dataaccess.api.dao.OrderDao;
import com.wtulich.photosupp.orderhandling.dataaccess.api.entity.MediaContentEntity;
import com.wtulich.photosupp.orderhandling.dataaccess.api.entity.OrderEntity;
import com.wtulich.photosupp.orderhandling.logic.api.mapper.MediaContentMapper;
import com.wtulich.photosupp.orderhandling.logic.api.to.MediaContentEto;
import com.wtulich.photosupp.orderhandling.logic.api.usecase.UcFindMediaContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Validated
@Named
public class UcFindMediaContentImpl implements UcFindMediaContent {

    private static final Logger LOG = LoggerFactory.getLogger(UcFindMediaContentImpl.class);
    private static final String ID_CANNOT_BE_NULL = "id cannot be a null value";
    private static final String GET_ALL_MEDIA_CONTENT_LOG = "Get all Media Content of Order with id {} from database.";

    @Inject
    private MediaContentDao mediaContentDao;

    @Inject
    private OrderDao orderDao;

    @Inject
    private MediaContentMapper mediaContentMapper;

    @Override
    public Optional<List<MediaContentEto>> findAllMediaContentByOrderId(Long id) throws EntityDoesNotExistException {
        Objects.requireNonNull(id, ID_CANNOT_BE_NULL);
        OrderEntity orderEntity = orderDao.findById(id).orElseThrow(() ->
                new EntityDoesNotExistException("Order with id " + id + " does not exist."));

        LOG.debug(GET_ALL_MEDIA_CONTENT_LOG);

        return Optional.of(mediaContentDao.findAllByOrder_Id(orderEntity.getId()).stream()
                .map(mediaContentEntity -> toMediaContentEto(mediaContentEntity))
                .collect(Collectors.toList()));
    }

    private MediaContentEto toMediaContentEto(MediaContentEntity mediaContentEntity){
        MediaContentEto mediaContentEto = mediaContentMapper.toMediaContentEto(mediaContentEntity);
        mediaContentEto.setOrderId(mediaContentEntity.getOrder().getId());

        return mediaContentEto;
    }
}
