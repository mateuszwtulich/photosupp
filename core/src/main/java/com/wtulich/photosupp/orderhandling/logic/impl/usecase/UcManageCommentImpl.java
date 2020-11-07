package com.wtulich.photosupp.orderhandling.logic.impl.usecase;

import com.wtulich.photosupp.general.logic.api.exception.EntityDoesNotExistException;
import com.wtulich.photosupp.orderhandling.dataaccess.api.dao.CommentDao;
import com.wtulich.photosupp.orderhandling.dataaccess.api.dao.OrderDao;
import com.wtulich.photosupp.orderhandling.logic.api.mapper.CommentMapper;
import com.wtulich.photosupp.orderhandling.logic.api.to.CommentEto;
import com.wtulich.photosupp.orderhandling.logic.api.to.CommentTo;
import com.wtulich.photosupp.orderhandling.logic.api.usecase.UcManageComment;
import com.wtulich.photosupp.userhandling.dataaccess.api.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

@Validated
@Named
public class UcManageCommentImpl implements UcManageComment {

    private static final Logger LOG = LoggerFactory.getLogger(UcManageCommentImpl.class);
    private static final String ID_CANNOT_BE_NULL = "id cannot be a null value";
    private static final String CREATE_COMMENT_LOG = "Create Comment with content {} in database.";
    private static final String UPDATE_COMMENT_LOG = "Update Comment with id {} from database.";

    @Inject
    private CommentDao commentDao;

    @Inject
    private OrderDao orderDao;

    @Inject
    private UserDao userDao;

    @Inject
    private CommentMapper commentMapper;

    @Override
    public Optional<CommentEto> createComment(CommentTo commentTo) throws EntityDoesNotExistException {
        return Optional.empty();
    }

    @Override
    public Optional<CommentEto> updateComment(CommentTo commentTo, Long id) throws EntityDoesNotExistException {
        return Optional.empty();
    }
}
