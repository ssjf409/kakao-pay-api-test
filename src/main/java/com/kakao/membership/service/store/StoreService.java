package com.kakao.membership.service.store;

import com.kakao.membership.converter.StoreDtoConverter;
import com.kakao.membership.repository.store.StoreRepository;
import com.kakao.membership.service.dto.StoreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class StoreService {

    private final StoreRepository storeRepository;

    @Transactional(readOnly = true)
    public StoreDto findStore(Long storeId) {
        if (storeId == null) {
            return null;
        }
        return StoreDtoConverter.convert(storeRepository.findStoreByStoreId(storeId));
    }
}
