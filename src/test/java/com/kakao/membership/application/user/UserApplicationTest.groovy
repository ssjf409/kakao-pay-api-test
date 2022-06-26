package com.kakao.membership.application.user

import com.kakao.membership.domain.entity.user.Users
import com.kakao.membership.exception.NotFoundUserException
import com.kakao.membership.repository.user.UserRepository
import com.kakao.membership.service.user.UserService
import spock.lang.Specification

class UserApplicationTest extends Specification {

    UserRepository userRepository = Mock()
    UserService userService
    UserApplication sut

    void setup() {
        userService = new UserService(userRepository)
        sut = new UserApplication(userService)
    }

    def "save user"() {
        given:
        Long userId = 1L
        userRepository.findByUserId(_ as Long) >> Users.builder().userId(userId).build()

        when:
        def result = sut.findUser(userId)

        then:
        result.getUserId() == userId
        noExceptionThrown()
    }

    def "can't save user with null value"() {
        given:
        Long userId = null
        userRepository.findByUserId(_ as Long) >> Users.builder().userId(userId).build()

        when:
        sut.findUser(userId)

        then:
        thrown(NotFoundUserException.class)
    }
}
