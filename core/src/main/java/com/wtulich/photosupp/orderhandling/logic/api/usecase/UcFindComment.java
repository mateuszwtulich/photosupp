package com.wtulich.photosupp.orderhandling.logic.api.usecase;

import com.wtulich.photosupp.orderhandling.logic.api.to.CommentEto;

import java.util.List;
import java.util.Optional;

public interface UcFindComment {

    Optional<List<CommentEto>> findAllCommentsByOrderId(Long id);
}