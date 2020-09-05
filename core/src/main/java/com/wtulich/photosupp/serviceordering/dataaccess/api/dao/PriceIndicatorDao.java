package com.wtulich.photosupp.serviceordering.dataaccess.api.dao;

import com.wtulich.photosupp.serviceordering.dataaccess.api.entity.PriceIndicatorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceIndicatorDao extends JpaRepository<PriceIndicatorEntity, Long> {
}
