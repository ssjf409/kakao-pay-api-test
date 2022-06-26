package com.kakao.membership.service.membership

import com.kakao.membership.domain.entity.membership.Membership
import com.kakao.membership.repository.member.MembershipRepository
import spock.lang.Specification

class MembershipServiceTest extends Specification {

    MembershipRepository membershipRepository = Mock()
    MembershipService sut

    void setup() {
        sut = new MembershipService(membershipRepository)
    }

    def "serve existing membership"() {
        given:
        Long userId = 1L
        membershipRepository.findMembershipByUserId(_ as Long)
                >> Membership.builder().membershipId(123L).build()

        when:
        def membershipDto = sut.findOrCreate(userId)

        then:
        membershipDto.getMembershipId() == 123L
        0 * membershipRepository.save(_ as Membership)
        noExceptionThrown()
    }

    def "create a new membership and serve"() {
        given:
        Long userId = 1L
        membershipRepository.findMembershipByUserId(_ as Long) >> null

        when:
        sut.findOrCreate(userId)

        then:
        1 * membershipRepository.save(_ as Membership)
        noExceptionThrown()
    }

    def "find a membership with barcode"() {
        given:
        String barcode = "1234567890"
        Long membershipId = 123L
        membershipRepository.findMembershipByBarcode(_ as String) >> Membership.builder().membershipId(membershipId).barcode(barcode).build()

        when:
        def result = sut.findByBarcode(barcode)

        then:
        result.getMembershipId() == membershipId
        result.getBarcode() == barcode
        noExceptionThrown()
    }

    def "can't find a membership with invalid barcode"() {
        given:
        String barcode = "123"
        Long membershipId = 123L
        membershipRepository.findMembershipByBarcode(_ as String) >> null

        when:
        def result = sut.findByBarcode(barcode)

        then:
        result == null
    }

    def "can't find a membership without barcode"() {
        given:
        String barcode = null
        Long membershipId = 123L
        membershipRepository.findMembershipByBarcode(_ as String) >> null

        when:
        def result = sut.findByBarcode(barcode)

        then:
        result == null
    }
}
