package com.wtulich.photosupp.orderhandling.dataaccess.api.dao;

import com.wtulich.photosupp.orderhandling.dataaccess.api.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<CommentEntity, Long> {
}
