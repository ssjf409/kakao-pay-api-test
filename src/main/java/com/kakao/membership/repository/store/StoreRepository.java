package com.kakao.membership.repository.store;

import com.kakao.membership.domain.entity.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findStoreByStoreId(Long storeId);
}
