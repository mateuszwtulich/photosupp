package com.wtulich.photosupp.serviceordering.logic.impl.usecase;

import com.wtulich.photosupp.serviceordering.dataaccess.api.dao.ServiceDao;
import com.wtulich.photosupp.serviceordering.logic.api.mapper.ServiceMapper;
import com.wtulich.photosupp.serviceordering.logic.api.to.ServiceEto;
import com.wtulich.photosupp.serviceordering.logic.api.usecase.UcFindService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Validated
@Named
public class UcFindServiceImpl implements UcFindService {

    private static final Logger LOG = LoggerFactory.getLogger(UcFindServiceImpl.class);
    private static final String GET_ALL_SERVICES_LOG = "Get all Services from database.";

    @Inject
    private ServiceDao serviceDao;

    @Inject
    private ServiceMapper serviceMapper;

    @Override
    public Optional<List<ServiceEto>> findAllServices() {
        LOG.debug(GET_ALL_SERVICES_LOG);

        return Optional.of(serviceDao.findAll().stream()
                .map(serviceEntity -> serviceMapper.toServiceEto(serviceEntity))
                .collect(Collectors.toList()));
    }
}
