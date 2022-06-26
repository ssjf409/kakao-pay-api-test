package com.kakao.membership.service.point

import com.kakao.membership.domain.entity.point.PointHistory
import com.kakao.membership.domain.entity.store.IndustryType
import com.kakao.membership.repository.point.PointHistoryRepository
import spock.lang.Specification

import java.time.LocalDateTime

class PointHistoryServiceTest extends Specification {

    PointHistoryRepository pointHistoryRepository = Mock()
    PointHistoryService sut

    void setup() {
        sut = new PointHistoryService(pointHistoryRepository)
    }

    def "save once"() {
        given:
        Long membershipId = 1L
        Long storeId = 5L
        IndustryType industryType = IndustryType.GROCERY
        String storeName = "오늘만 마트"
        Long amount = 1000

        when:
        sut.save(membershipId, storeId, industryType, storeName, amount)

        then:
        1 * pointHistoryRepository.save(_ as PointHistory)
        noExceptionThrown()
    }

    def "search history"() {
        given:
        Long membershipId = 1L
        LocalDateTime startDate = LocalDateTime.now()
        LocalDateTime endDate = LocalDateTime.now().plusHours(10)
        pointHistoryRepository.findAllByMembershipIdAndCreatedAtBetween(_ as Long, _ as LocalDateTime, _ as LocalDateTime)
                >> List.of(
                PointHistory.builder().membershipId(1L).build(),
                PointHistory.builder().membershipId(2L).build(),
                PointHistory.builder().membershipId(3L).build()
        )

        when:
        sut.searchHistory(membershipId, startDate, endDate)

        then:
        1 * pointHistoryRepository.findAllByMembershipIdAndCreatedAtBetween(_ as Long, _ as LocalDateTime, _ as LocalDateTime)
        noExceptionThrown()
    }

    def "end date can't be early than start date"() {
        given:
        Long membershipId = 1L
        LocalDateTime startDate = LocalDateTime.now().plusHours(10)
        LocalDateTime endDate = LocalDateTime.now()
        pointHistoryRepository.findAllByMembershipIdAndCreatedAtBetween(_ as Long, _ as LocalDateTime, _ as LocalDateTime)
                >> List.of(
                PointHistory.builder().membershipId(1L).build(),
                PointHistory.builder().membershipId(2L).build(),
                PointHistory.builder().membershipId(3L).build()
        )

        when:
        sut.searchHistory(membershipId, startDate, endDate)

        then:
        0 * pointHistoryRepository.findAllByMembershipIdAndCreatedAtBetween(_ as Long, _ as LocalDateTime, _ as LocalDateTime)
        noExceptionThrown()
    }

    def "membershipId can't be empty"() {
        given:
        LocalDateTime startDate = LocalDateTime.now().plusHours(10)
        LocalDateTime endDate = LocalDateTime.now()
        pointHistoryRepository.findAllByMembershipIdAndCreatedAtBetween(_ as Long, _ as LocalDateTime, _ as LocalDateTime)
                >> List.of(
                PointHistory.builder().membershipId(1L).build(),
                PointHistory.builder().membershipId(2L).build(),
                PointHistory.builder().membershipId(3L).build()
        )

        when:
        sut.searchHistory(null, startDate, endDate)

        then:
        0 * pointHistoryRepository.findAllByMembershipIdAndCreatedAtBetween(_ as Long, _ as LocalDateTime, _ as LocalDateTime)
        noExceptionThrown()
    }

    def "startDate can't be empty"() {
        given:
        Long membershipId = 1L
        LocalDateTime endDate = LocalDateTime.now()
        pointHistoryRepository.findAllByMembershipIdAndCreatedAtBetween(_ as Long, _ as LocalDateTime, _ as LocalDateTime)
                >> List.of(
                PointHistory.builder().membershipId(1L).build(),
                PointHistory.builder().membershipId(2L).build(),
                PointHistory.builder().membershipId(3L).build()
        )

        when:
        sut.searchHistory(membershipId, null, endDate)

        then:
        0 * pointHistoryRepository.findAllByMembershipIdAndCreatedAtBetween(_ as Long, _ as LocalDateTime, _ as LocalDateTime)
        noExceptionThrown()
    }

    def "endDate can't be empty"() {
        given:
        Long membershipId = 1L
        LocalDateTime startDate = LocalDateTime.now().plusHours(10)
        pointHistoryRepository.findAllByMembershipIdAndCreatedAtBetween(_ as Long, _ as LocalDateTime, _ as LocalDateTime)
                >> List.of(
                PointHistory.builder().membershipId(1L).build(),
                PointHistory.builder().membershipId(2L).build(),
                PointHistory.builder().membershipId(3L).build()
        )

        when:
        sut.searchHistory(membershipId, startDate, null)

        then:
        0 * pointHistoryRepository.findAllByMembershipIdAndCreatedAtBetween(_ as Long, _ as LocalDateTime, _ as LocalDateTime)
        noExceptionThrown()
    }
}
