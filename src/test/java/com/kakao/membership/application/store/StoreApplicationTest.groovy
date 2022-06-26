package com.kakao.membership.application.store

import com.kakao.membership.domain.entity.store.Store
import com.kakao.membership.exception.NotFoundStoreException
import com.kakao.membership.repository.store.StoreRepository
import com.kakao.membership.service.store.StoreService
import spock.lang.Specification

class StoreApplicationTest extends Specification {

    StoreRepository storeRepository = Mock()
    StoreService storeService
    StoreApplication sut

    void setup() {
        storeService = new StoreService(storeRepository)
        sut = new StoreApplication(storeService)
    }

    def "success to find store"() {
        given:
        Long storeId = 123L
        storeRepository.findStoreByStoreId(storeId) >> Store.builder().storeId(storeId).build()

        when:
        def result = sut.findStore(storeId)

        then:
        result.getStoreId() == storeId
        noExceptionThrown()
    }

    def "can't success to find store without storeId"() {
        given:
        Long storeId = null
        storeRepository.findStoreByStoreId(storeId) >> Store.builder().storeId(storeId).build()

        when:
        sut.findStore(storeId)

        then:
        thrown(NotFoundStoreException.class)
    }
}
