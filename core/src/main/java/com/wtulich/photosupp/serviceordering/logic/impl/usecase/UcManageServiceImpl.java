package com.wtulich.photosupp.serviceordering.logic.impl.usecase;

import com.wtulich.photosupp.general.logic.api.exception.EntityAlreadyExistsException;
import com.wtulich.photosupp.general.logic.api.exception.EntityDoesNotExistException;
import com.wtulich.photosupp.serviceordering.dataaccess.api.dao.ServiceDao;
import com.wtulich.photosupp.serviceordering.dataaccess.api.entity.ServiceEntity;
import com.wtulich.photosupp.serviceordering.logic.api.mapper.ServiceMapper;
import com.wtulich.photosupp.serviceordering.logic.api.to.ServiceEto;
import com.wtulich.photosupp.serviceordering.logic.api.to.ServiceTo;
import com.wtulich.photosupp.serviceordering.logic.api.usecase.UcManageService;
import com.wtulich.photosupp.serviceordering.logic.impl.validator.ServiceValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;
import java.util.Optional;

@Validated
@Named
public class UcManageServiceImpl implements UcManageService {

    private static final Logger LOG = LoggerFactory.getLogger(UcManageServiceImpl.class);
    private static final String ID_CANNOT_BE_NULL = "id cannot be a null value";
    private static final String CREATE_SERVICE_LOG = "Create Service with name {} in database.";
    private static final String UPDATE_SERVICE_LOG = "Update Service with id {} from database.";

    @Inject
    private ServiceDao serviceDao;

    @Inject
    private ServiceMapper serviceMapper;

    @Inject
    private ServiceValidator serviceValidator;

    @Override
    public Optional<ServiceEto> createService(ServiceTo serviceTo) throws EntityAlreadyExistsException {
        serviceValidator.verifyIfServiceNameAlreadyExists(serviceTo.getName());
        LOG.debug(CREATE_SERVICE_LOG, serviceTo.getName());

        ServiceEntity serviceEntity = serviceMapper.toServiceEntity(serviceTo);
        return Optional.of(serviceMapper.toServiceEto(serviceDao.save(serviceEntity)));
    }

    @Override
    public Optional<ServiceEto> updateService(ServiceTo serviceTo, Long id) throws EntityDoesNotExistException,
            EntityAlreadyExistsException {

        Objects.requireNonNull(id, ID_CANNOT_BE_NULL);

        ServiceEntity serviceEntity = serviceDao.findById(id).orElseThrow(() ->
                new EntityDoesNotExistException("Service with id " + id + " does not exist."));

        if(!serviceEntity.getName().equals(serviceTo.getName())){
            serviceValidator.verifyIfServiceNameAlreadyExists(serviceTo.getName());
            serviceEntity.setName(serviceTo.getName());
        }

        LOG.debug(UPDATE_SERVICE_LOG, id);
        serviceEntity.setDescription(serviceTo.getDescription());
        serviceEntity.setBasePrice(serviceTo.getBasePrice());

        return Optional.of(serviceMapper.toServiceEto(serviceEntity));
    }
}
