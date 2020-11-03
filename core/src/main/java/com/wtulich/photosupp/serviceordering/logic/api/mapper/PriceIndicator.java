package com.wtulich.photosupp.serviceordering.logic.api.mapper;

import com.wtulich.photosupp.serviceordering.dataaccess.api.entity.PriceIndicatorEntity;
import com.wtulich.photosupp.serviceordering.logic.api.to.PriceIndicatorEto;
import com.wtulich.photosupp.serviceordering.logic.api.to.PriceIndicatorTo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceIndicator {
    PriceIndicatorEntity toPriceIndicatorEntity(PriceIndicatorTo priceIndicatorTo);

    PriceIndicatorEto toPriceIndicatorEto(PriceIndicatorEntity priceIndicatorEntity);
}
