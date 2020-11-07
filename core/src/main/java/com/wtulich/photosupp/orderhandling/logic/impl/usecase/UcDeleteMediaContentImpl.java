package com.wtulich.photosupp.orderhandling.logic.impl.usecase;

import com.wtulich.photosupp.general.logic.api.exception.EntityDoesNotExistException;
import com.wtulich.photosupp.orderhandling.dataaccess.api.dao.MediaContentDao;
import com.wtulich.photosupp.orderhandling.logic.api.usecase.UcDeleteMediaContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;

import javax.inject.Inject;
import javax.inject.Named;

@Validated
@Named
public class UcDeleteMediaContentImpl implements UcDeleteMediaContent {

    private static final Logger LOG = LoggerFactory.getLogger(UcDeleteMediaContentImpl.class);
    private static final String DELETE_MEDIA_CONTENT_LOG = "Delete Media Content with id {} in database.";
    private static final String DELETE_ALL_MEDIA_CONTENT_LOG = "Delete all Media Content of Order id {} in database.";

    @Inject
    private MediaContentDao mediaContentDao;

    @Override
    public void deleteMediaContent(Long id) throws EntityDoesNotExistException {

    }

    @Override
    public void deleteAllMediaContent(Long id) throws EntityDoesNotExistException {

    }
}
