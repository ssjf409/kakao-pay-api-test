package com.kakao.membership.application.membership

import com.kakao.membership.application.user.UserApplication
import com.kakao.membership.domain.entity.membership.Membership
import com.kakao.membership.domain.entity.user.Users
import com.kakao.membership.exception.NotFoundMembershipException
import com.kakao.membership.exception.NotFoundUserException
import com.kakao.membership.repository.member.MembershipRepository
import com.kakao.membership.repository.user.UserRepository
import com.kakao.membership.service.membership.MembershipService
import com.kakao.membership.service.user.UserService
import spock.lang.Specification

class MembershipApplicationTest extends Specification {

    UserRepository userRepository = Mock()
    UserService userService
    UserApplication userApplication

    MembershipRepository membershipRepository = Mock()
    MembershipService membershipService

    MembershipApplication sut

    void setup() {
        userService = new UserService(userRepository)
        userApplication = new UserApplication(userService)
        membershipService = new MembershipService(membershipRepository)

        sut = new MembershipApplication(userApplication, membershipService)
    }

    def "success to find or create membership"() {
        given:
        Long userId = 123L
        Long membershipId = 456L
        Membership membership = Membership.builder().membershipId(membershipId).userId(userId).build()
        userRepository.findByUserId(_ as Long) >> Users.builder().userId(userId).build()
        membershipRepository.findMembershipByUserId(userId) >> membership
        membershipRepository.save(_ as Membership) >> membership

        when:
        def result = sut.findOrCreateMembership(userId)

        then:
        result.getUserId() == userId

    }

    def "can't find or create membership without userId"() {
        given:
        Long userId = null
        Long membershipId = 456L
        Membership membership = Membership.builder().membershipId(membershipId).userId(userId).build()
        userRepository.findByUserId(_ as Long) >> Users.builder().userId(userId).build()
        membershipRepository.findMembershipByUserId(userId) >> membership
        membershipRepository.save(_ as Membership) >> membership

        when:
        sut.findOrCreateMembership(userId)

        then:
        thrown(NotFoundUserException.class)
    }

    def "success to find membership with barcode"() {
        given:
        String barcode = "1234567890"
        Long membershipId = 123L
        membershipRepository.findMembershipByBarcode(_ as String) >> Membership.builder().membershipId(membershipId).barcode(barcode).build()

        when:
        def result = sut.findMembershipByBarcode(barcode)

        then:
        result.getBarcode() == barcode
        result.getMembershipId() == membershipId
        noExceptionThrown()
    }

    def "can't success to find membership without barcode"() {
        given:
        String barcode = null
        Long membershipId = 123L
        membershipRepository.findMembershipByBarcode(_ as String) >> Membership.builder().membershipId(membershipId).barcode(barcode).build()

        when:
        sut.findMembershipByBarcode(barcode)

        then:
        thrown(NotFoundMembershipException.class)

    }

    def "can't success to find membership with invalid barcode"() {
        given:
        String barcode = "12345"
        Long membershipId = 123L
        membershipRepository.findMembershipByBarcode(_ as String) >> null

        when:
        sut.findMembershipByBarcode(barcode)

        then:
        thrown(NotFoundMembershipException.class)

    }

}
