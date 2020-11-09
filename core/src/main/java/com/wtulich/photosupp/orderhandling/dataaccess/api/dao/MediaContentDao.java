package com.wtulich.photosupp.orderhandling.dataaccess.api.dao;

import com.wtulich.photosupp.orderhandling.dataaccess.api.entity.MediaContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaContentDao extends JpaRepository<MediaContentEntity, Long> {

    void deleteAllByOrder_Id(Long id);

    List<MediaContentEntity> findAllByOrder_Id(Long id);
}
