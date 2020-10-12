package com.wtulich.photosupp.userhandling.logic.api.usecase;

public interface UcDeleteUser {

    void deleteUserAndAllRelatedEntities(Long userId);
}
