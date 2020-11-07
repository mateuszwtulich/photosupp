package com.wtulich.photosupp.orderhandling.logic.impl.usecase;

import com.wtulich.photosupp.general.logic.api.exception.EntityDoesNotExistException;
import com.wtulich.photosupp.orderhandling.dataaccess.api.dao.CommentDao;
import com.wtulich.photosupp.orderhandling.logic.api.mapper.CommentMapper;
import com.wtulich.photosupp.orderhandling.logic.api.to.CommentEto;
import com.wtulich.photosupp.orderhandling.logic.api.usecase.UcFindComment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Optional;

@Validated
@Named
public class UcFindCommentImpl implements UcFindComment {

    private static final Logger LOG = LoggerFactory.getLogger(UcFindCommentImpl.class);
    private static final String ID_CANNOT_BE_NULL = "id cannot be a null value";
    private static final String GET_ALL_COMMENTS_LOG = "Get all Comments of Order with id {} from database.";

    @Inject
    private CommentDao commentDao;

    @Inject
    private CommentMapper commentMapper;

    @Override
    public Optional<List<CommentEto>> findAllCommentsByOrderId(Long id) throws EntityDoesNotExistException {
        return Optional.empty();
    }
}
