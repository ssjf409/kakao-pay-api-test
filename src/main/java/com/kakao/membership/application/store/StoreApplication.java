package com.kakao.membership.application.store;

import com.kakao.membership.exception.NotFoundStoreException;
import com.kakao.membership.service.dto.StoreDto;
import com.kakao.membership.service.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class StoreApplication {

    private final StoreService storeService;

    public StoreDto findStore(Long storeId) {
        return Optional.ofNullable(storeService.findStore(storeId))
                       .orElseThrow(() -> new NotFoundStoreException(
                               String.format("failed to find store, storeId: %s", storeId)));
    }
}
