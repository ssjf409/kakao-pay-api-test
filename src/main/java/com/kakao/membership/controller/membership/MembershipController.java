package com.kakao.membership.controller.membership;

import com.kakao.membership.application.membership.MembershipApplication;
import com.kakao.membership.controller.dto.membership.MembershipCreateResponse;
import com.kakao.membership.domain.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/membership")
@RestController
public class MembershipController {

    private final MembershipApplication membershipApplication;

    @GetMapping
    public Response<MembershipCreateResponse> findOrCreateMembership(@RequestParam Long userId) {
        return Response.ok(membershipApplication.findOrCreateMembership(userId));
    }
}
