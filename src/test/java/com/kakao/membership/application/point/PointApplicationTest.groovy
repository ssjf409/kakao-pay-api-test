package com.kakao.membership.application.point

import com.kakao.membership.application.membership.MembershipApplication
import com.kakao.membership.application.store.StoreApplication
import com.kakao.membership.application.user.UserApplication
import com.kakao.membership.controller.dto.membership.PointChangeRequest
import com.kakao.membership.domain.entity.membership.Membership
import com.kakao.membership.domain.entity.point.Point
import com.kakao.membership.domain.entity.store.IndustryType
import com.kakao.membership.domain.entity.store.Store
import com.kakao.membership.exception.NotFoundMembershipException
import com.kakao.membership.exception.NotFoundStoreException
import com.kakao.membership.repository.member.MembershipRepository
import com.kakao.membership.repository.point.PointHistoryRepository
import com.kakao.membership.repository.point.PointRepository
import com.kakao.membership.repository.store.StoreRepository
import com.kakao.membership.repository.user.UserRepository
import com.kakao.membership.service.membership.MembershipService
import com.kakao.membership.service.point.PointHistoryService
import com.kakao.membership.service.point.PointService
import com.kakao.membership.service.store.StoreService
import com.kakao.membership.service.user.UserService
import spock.lang.Specification

class PointApplicationTest extends Specification {

    StoreRepository storeRepository = Mock()
    StoreService storeService
    StoreApplication storeApplication

    UserRepository userRepository = Mock()
    UserService userService
    UserApplication userApplication
    MembershipRepository membershipRepository = Mock()
    MembershipService membershipService
    MembershipApplication membershipApplication

    PointRepository pointRepository = Mock()
    PointHistoryRepository pointHistoryRepository = Mock()
    PointHistoryService pointHistoryService
    PointService pointService

    PointApplication sut

    void setup() {
        storeService = new StoreService(storeRepository)
        storeApplication = new StoreApplication(storeService)

        userService = new UserService(userRepository)
        userApplication = new UserApplication(userService)

        membershipService = new MembershipService(membershipRepository)
        membershipApplication = new MembershipApplication(userApplication, membershipService)
        pointHistoryService = new PointHistoryService(pointHistoryRepository)
        pointService = new PointService(pointRepository, pointHistoryService)

        sut = new PointApplication(storeApplication, membershipApplication, pointService, pointHistoryService)
    }

    def "success to change point"() {
        given:
        Long storeId = 123L
        String barcode = "1234567890"
        Long amount = 3000
        Long membershipId = 456L
        Long pointId = 6789L
        IndustryType industryType = IndustryType.GROCERY
        PointChangeRequest pointChangeRequest = new PointChangeRequest(storeId: storeId, barcode: barcode, amount: amount)
        def membership = Membership.builder().membershipId(membershipId).barcode(barcode).build()
        storeRepository.findStoreByStoreId(_ as Long) >> Store.builder().storeId(storeId).industryType(industryType).build()
        membershipRepository.findMembershipByBarcode(_ as String) >> membership
        pointRepository.findPointByMembershipIdAndIndustryType(_ as Long, _ as IndustryType) >> null
        pointRepository.save(_ as Point) >> Point.builder().pointId(pointId).build()

        when:
        def result = sut.changePoint(pointChangeRequest)

        then:
        result.getPointId() == pointId
        noExceptionThrown()
    }

    def "can't success to change point without storeId"() {
        given:
        Long storeId = null
        String barcode = "1234567890"
        Long amount = 3000
        Long membershipId = 456L
        Long pointId = 6789L
        IndustryType industryType = IndustryType.GROCERY
        PointChangeRequest pointChangeRequest = new PointChangeRequest(storeId: storeId, barcode: barcode, amount: amount)
        def membership = Membership.builder().membershipId(membershipId).barcode(barcode).build()
        storeRepository.findStoreByStoreId(_ as Long) >> Store.builder().storeId(storeId).industryType(industryType).build()
        membershipRepository.findMembershipByBarcode(_ as String) >> membership
        pointRepository.findPointByMembershipIdAndIndustryType(_ as Long, _ as IndustryType) >> null
        pointRepository.save(_ as Point) >> Point.builder().pointId(pointId).build()

        when:
        sut.changePoint(pointChangeRequest)

        then:
        thrown(NotFoundStoreException.class)
    }

    def "can't success to change point without barcode"() {
        given:
        Long storeId = 123L
        String barcode = "123"
        Long amount = 3000
        Long membershipId = 456L
        Long pointId = 6789L
        IndustryType industryType = IndustryType.GROCERY
        PointChangeRequest pointChangeRequest = new PointChangeRequest(storeId: storeId, barcode: barcode, amount: amount)
        storeRepository.findStoreByStoreId(_ as Long) >> Store.builder().storeId(storeId).industryType(industryType).build()
        membershipRepository.findMembershipByBarcode(_ as String) >> null
        pointRepository.findPointByMembershipIdAndIndustryType(_ as Long, _ as IndustryType) >> null
        pointRepository.save(_ as Point) >> Point.builder().pointId(pointId).build()

        when:
        sut.changePoint(pointChangeRequest)

        then:
        thrown(NotFoundMembershipException.class)
    }

    def "can't success to change point with invalid barcode"() {
        given:
        Long storeId = 123L
        String barcode = null
        Long amount = 3000
        Long membershipId = 456L
        Long pointId = 6789L
        IndustryType industryType = IndustryType.GROCERY
        PointChangeRequest pointChangeRequest = new PointChangeRequest(storeId: storeId, barcode: barcode, amount: amount)
        def membership = Membership.builder().membershipId(membershipId).barcode(barcode).build()
        storeRepository.findStoreByStoreId(_ as Long) >> Store.builder().storeId(storeId).industryType(industryType).build()
        membershipRepository.findMembershipByBarcode(_ as String) >> membership
        pointRepository.findPointByMembershipIdAndIndustryType(_ as Long, _ as IndustryType) >> null
        pointRepository.save(_ as Point) >> Point.builder().pointId(pointId).build()

        when:
        sut.changePoint(pointChangeRequest)

        then:
        thrown(NotFoundMembershipException.class)
    }
}
