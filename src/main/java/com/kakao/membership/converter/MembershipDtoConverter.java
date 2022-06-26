package com.kakao.membership.converter;

import com.kakao.membership.domain.entity.membership.Membership;
import com.kakao.membership.service.dto.MembershipDto;

public class MembershipDtoConverter {

    public static MembershipDto convert(Membership membership) {
        if (membership == null) {
            return null;
        }
        return MembershipDto.builder()
                            .membershipId(membership.getMembershipId())
                            .userId(membership.getUserId())
                            .barcode(membership.getBarcode())
                            .build();
    }
}
