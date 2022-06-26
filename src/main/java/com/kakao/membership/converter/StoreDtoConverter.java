package com.kakao.membership.converter;

import com.kakao.membership.domain.entity.store.Store;
import com.kakao.membership.service.dto.StoreDto;

public class StoreDtoConverter {

    public static StoreDto convert(Store store) {
        if (store == null) {
            return null;
        }
        return StoreDto.builder()
                       .storeId(store.getStoreId())
                       .storeName(store.getStoreName())
                       .industryType(store.getIndustryType())
                       .build();
    }
}
