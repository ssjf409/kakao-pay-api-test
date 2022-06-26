package com.kakao.membership.converter;

import com.kakao.membership.domain.entity.user.Users;
import com.kakao.membership.service.dto.UserDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserDtoConverter {

    public static UserDto convert(Users users) {
        if (users == null) {
            return null;
        }
        return UserDto.builder()
                      .userId(users.getUserId())
                      .loginId(users.getLoginId())
                      .email(users.getEmail())
                      .username(users.getUsername())
                      .dormant(users.isDormant())
                      .dormantAt(users.getDormantAt())
                      .withdrawn(users.isWithdrawn())
                      .withdrawnAt(users.getWithdrawnAt())
                      .build();
    }
}
