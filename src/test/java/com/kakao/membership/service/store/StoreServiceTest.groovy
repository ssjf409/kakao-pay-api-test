package com.kakao.membership.service.store

import com.kakao.membership.domain.entity.store.Store
import com.kakao.membership.repository.store.StoreRepository
import spock.lang.Specification

class StoreServiceTest extends Specification {
    StoreRepository storeRepository = Mock()
    StoreService sut

    void setup() {
        sut = new StoreService(storeRepository)
    }

    def "success to find store"() {
        given:
        Long storeId = 10L
        storeRepository.findStoreByStoreId(_ as Long) >> Store.builder().storeId(storeId).build()

        when:
        def result = sut.findStore(storeId)

        then:
        result.getStoreId() == storeId
        noExceptionThrown()
    }

    def "get null if put null"() {
        when:
        def result = sut.findStore(null)

        then:
        result == null
        noExceptionThrown()
    }
}
