package com.kakao.membership.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDto {

    private Long userId;

    private String loginId;

    private String email;

    private String username;

    private boolean dormant;

    private LocalDateTime dormantAt;

    private boolean withdrawn;

    private LocalDateTime withdrawnAt;
}
