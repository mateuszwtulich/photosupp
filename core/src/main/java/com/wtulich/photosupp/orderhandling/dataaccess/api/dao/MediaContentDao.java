package com.wtulich.photosupp.orderhandling.dataaccess.api.dao;

import com.wtulich.photosupp.orderhandling.dataaccess.api.entity.MediaContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaContentDao extends JpaRepository<MediaContentEntity, Long> {
}
