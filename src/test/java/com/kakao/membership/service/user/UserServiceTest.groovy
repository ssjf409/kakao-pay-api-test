package com.kakao.membership.service.user

import com.kakao.membership.domain.entity.user.Users
import com.kakao.membership.repository.user.UserRepository
import spock.lang.Specification

class UserServiceTest extends Specification {

    UserRepository userRepository = Mock()
    UserService sut

    void setup() {
        sut = new UserService(userRepository)
    }

    def "success to find user"() {
        given:
        Long userId = 10L
        userRepository.findByUserId(_ as Long) >> Users.builder().userId(userId).build()

        when:
        def result = sut.findUser(userId)

        then:
        result.getUserId() == userId
        noExceptionThrown()
    }

    def "get null if put null"() {
        when:
        def result = sut.findUser(null)

        then:
        result == null
        noExceptionThrown()
    }
}
