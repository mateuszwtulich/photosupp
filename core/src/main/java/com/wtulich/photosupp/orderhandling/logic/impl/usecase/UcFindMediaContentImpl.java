package com.wtulich.photosupp.orderhandling.logic.impl.usecase;

import com.wtulich.photosupp.general.logic.api.exception.EntityDoesNotExistException;
import com.wtulich.photosupp.orderhandling.dataaccess.api.dao.CommentDao;
import com.wtulich.photosupp.orderhandling.dataaccess.api.dao.MediaContentDao;
import com.wtulich.photosupp.orderhandling.logic.api.mapper.CommentMapper;
import com.wtulich.photosupp.orderhandling.logic.api.mapper.MediaContentMapper;
import com.wtulich.photosupp.orderhandling.logic.api.to.MediaContentEto;
import com.wtulich.photosupp.orderhandling.logic.api.usecase.UcFindMediaContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Optional;

@Validated
@Named
public class UcFindMediaContentImpl implements UcFindMediaContent {

    private static final Logger LOG = LoggerFactory.getLogger(UcFindMediaContentImpl.class);
    private static final String ID_CANNOT_BE_NULL = "id cannot be a null value";
    private static final String GET_ALL_MEDIA_CONTENT_LOG = "Get all Media Content of Order with id {} from database.";

    @Inject
    private MediaContentDao mediaContentDao;

    @Inject
    private MediaContentMapper mediaContentMapper;

    @Override
    public Optional<List<MediaContentEto>> findAllMediaContentByOrderId(Long id) throws EntityDoesNotExistException {
        return Optional.empty();
    }
}
