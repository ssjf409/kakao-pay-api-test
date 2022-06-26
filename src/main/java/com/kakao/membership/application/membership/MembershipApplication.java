package com.kakao.membership.application.membership;

import com.kakao.membership.application.user.UserApplication;
import com.kakao.membership.controller.dto.membership.MembershipCreateResponse;
import com.kakao.membership.exception.NotFoundMembershipException;
import com.kakao.membership.service.dto.MembershipDto;
import com.kakao.membership.service.dto.UserDto;
import com.kakao.membership.service.membership.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class MembershipApplication {

    private final UserApplication userApplication;
    private final MembershipService membershipService;

    public MembershipCreateResponse findOrCreateMembership(Long userId) {
        UserDto userDto = userApplication.findUser(userId);
        MembershipDto membership = membershipService.findOrCreate(userDto.getUserId());

        return MembershipCreateResponse.builder()
                                       .userId(membership.getUserId())
                                       .barcode(membership.getBarcode())
                                       .build();
    }

    public MembershipDto findMembershipByBarcode(String barcode) {
        return Optional.ofNullable(membershipService.findByBarcode(barcode)).orElseThrow(
                () -> new NotFoundMembershipException(
                        String.format("failed to find membership, barcode: %s", barcode)));
    }
}
