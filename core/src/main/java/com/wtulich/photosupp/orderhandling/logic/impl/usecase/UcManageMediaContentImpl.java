package com.wtulich.photosupp.orderhandling.logic.impl.usecase;

import com.wtulich.photosupp.general.logic.api.exception.EntityDoesNotExistException;
import com.wtulich.photosupp.orderhandling.dataaccess.api.dao.MediaContentDao;
import com.wtulich.photosupp.orderhandling.logic.api.mapper.MediaContentMapper;
import com.wtulich.photosupp.orderhandling.logic.api.to.MediaContentEto;
import com.wtulich.photosupp.orderhandling.logic.api.to.MediaContentTo;
import com.wtulich.photosupp.orderhandling.logic.api.usecase.UcManageMediaContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

@Validated
@Named
public class UcManageMediaContentImpl implements UcManageMediaContent {

    private static final Logger LOG = LoggerFactory.getLogger(UcManageMediaContentImpl.class);
    private static final String ID_CANNOT_BE_NULL = "id cannot be a null value";
    private static final String CREATE_MEDIA_CONTENT_LOG = "Create Media Content with content {} in database.";

    @Inject
    private MediaContentDao mediaContentDao;

    @Inject
    private MediaContentMapper mediaContentMapper;

    @Override
    public Optional<MediaContentEto> addMediaContent(MediaContentTo mediaContentTo) throws EntityDoesNotExistException {
        return Optional.empty();
    }
}
