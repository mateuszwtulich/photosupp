package com.wtulich.photosupp.orderhandling.logic.api.mapper;

import com.wtulich.photosupp.orderhandling.dataaccess.api.entity.MediaContentEntity;
import com.wtulich.photosupp.orderhandling.logic.api.to.MediaContentEto;
import com.wtulich.photosupp.orderhandling.logic.api.to.MediaContentTo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IMediaContentMapper {

    MediaContentEntity toMediaContentEntity(MediaContentTo mediaContentTo);

    MediaContentEto toMediaContentEto(MediaContentEntity mediaContentEntity);
}
