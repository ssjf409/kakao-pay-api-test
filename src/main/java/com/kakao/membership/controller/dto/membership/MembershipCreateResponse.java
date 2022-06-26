package com.kakao.membership.controller.dto.membership;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MembershipCreateResponse {

    private Long userId;
    private String barcode;
}
