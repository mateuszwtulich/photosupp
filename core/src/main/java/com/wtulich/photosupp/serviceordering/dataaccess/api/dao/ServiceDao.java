package com.wtulich.photosupp.serviceordering.dataaccess.api.dao;

import com.wtulich.photosupp.serviceordering.dataaccess.api.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceDao extends JpaRepository<ServiceEntity, Long> {
}
