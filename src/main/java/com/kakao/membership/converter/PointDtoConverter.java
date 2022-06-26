package com.kakao.membership.converter;

import com.kakao.membership.domain.entity.point.Point;
import com.kakao.membership.service.dto.PointDto;

public class PointDtoConverter {
    public static PointDto convert(Point point) {
        if (point == null) {
            return null;
        }
        return PointDto.builder()
                       .pointId(point.getPointId())
                       .membershipId(point.getMembershipId())
                       .industryType(point.getIndustryType())
                       .amount(point.getAmount())
                       .build();
    }
}
