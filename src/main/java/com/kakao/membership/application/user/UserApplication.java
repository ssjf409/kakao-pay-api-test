package com.kakao.membership.application.user;

import com.kakao.membership.exception.NotFoundUserException;
import com.kakao.membership.service.dto.UserDto;
import com.kakao.membership.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserApplication {

    private final UserService userService;

    public UserDto findUser(Long userId) {
        return Optional.ofNullable(userService.findUser(userId))
                       .orElseThrow(() -> new NotFoundUserException(
                               String.format("failed to find user, userId: %s", userId)));
    }
}
