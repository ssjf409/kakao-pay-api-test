package com.kakao.membership.service.point

import com.kakao.membership.domain.entity.point.Point
import com.kakao.membership.domain.entity.store.IndustryType
import com.kakao.membership.exception.NegativePointException
import com.kakao.membership.repository.point.PointRepository
import spock.lang.Specification

class PointServiceTest extends Specification {
    PointRepository pointRepository = Mock()
    PointHistoryService pointHistoryService = Mock()
    PointService sut

    void setup() {
        sut = new PointService(pointRepository, pointHistoryService)
    }

    def "success to save point"() {
        given:
        Long membershipId = 1L
        Long storeId = 123L
        IndustryType industryType = IndustryType.GROCERY
        String storeName = "나만의 상점"
        Long amount = -3000
        Long pointId = 456L
        Point point = Point.builder()
                .pointId(pointId)
                .membershipId(membershipId)
                .industryType(industryType)
                .amount(amount)
                .build()
        pointRepository.findPointByMembershipIdAndIndustryType(_ as Long, _ as IndustryType) >> null
        pointRepository.save(_ as Point) >> point

        when:
        def result = sut.upsert(membershipId, storeId, industryType, storeName, amount)

        then:
        result.getMembershipId() == membershipId
        result.getIndustryType() == industryType
        result.getAmount() == amount
        1 * pointHistoryService.save(_, _, _, _, _)
        noExceptionThrown()
    }

    def "success to update point"() {
        given:
        Long membershipId = 1L
        Long storeId = 123L
        IndustryType industryType = IndustryType.GROCERY
        String storeName = "나만의 상점"
        Long savedAmount = 10000
        Long amount = -3000
        Long pointId = 456L
        Point point = Point.builder()
                .pointId(pointId)
                .membershipId(membershipId)
                .industryType(industryType)
                .amount(savedAmount)
                .build()
        Point savedPoint = Point.builder()
                .pointId(pointId)
                .membershipId(membershipId)
                .industryType(industryType)
                .amount(point.getAmount() + amount)
                .build()
        pointRepository.findPointByMembershipIdAndIndustryType(_ as Long, _ as IndustryType) >> point
        pointRepository.save(_ as Point) >> savedPoint

        when:
        def result = sut.upsert(membershipId, storeId, industryType, storeName, amount)

        then:
        result.getMembershipId() == membershipId
        result.getIndustryType() == industryType
        result.getAmount() == point.getAmount()
        1 * pointHistoryService.save(_, _, _, _, _)
        noExceptionThrown()
    }

    def "point's amount can't be minus"() {
        given:
        Long membershipId = 1L
        Long storeId = 123L
        IndustryType industryType = IndustryType.GROCERY
        String storeName = "나만의 상점"
        Long savedAmount = 10000
        Long amount = -13000
        Long pointId = 456L
        Point point = Point.builder()
                .pointId(pointId)
                .membershipId(membershipId)
                .industryType(industryType)
                .amount(savedAmount)
                .build()
        Point savedPoint = Point.builder()
                .pointId(pointId)
                .membershipId(membershipId)
                .industryType(industryType)
                .amount(point.getAmount() + amount)
                .build()
        pointRepository.findPointByMembershipIdAndIndustryType(_ as Long, _ as IndustryType) >> point
        pointRepository.save(_ as Point) >> savedPoint

        when:
        def result = sut.upsert(membershipId, storeId, industryType, storeName, amount)

        then:
        thrown(NegativePointException.class)
    }
}
