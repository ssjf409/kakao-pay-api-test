package com.kakao.membership.converter;

import com.kakao.membership.domain.entity.point.PointHistory;
import com.kakao.membership.service.dto.PointHistoryDto;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PointHistoryDtoConverter {

    public static List<PointHistoryDto> convert(List<PointHistory> pointHistories) {
        if (CollectionUtils.isEmpty(pointHistories)) {
            return Collections.emptyList();
        }
        return pointHistories.stream().map(PointHistoryDtoConverter::convert).collect(Collectors.toList());
    }

    public static PointHistoryDto convert(PointHistory pointHistory) {
        if (pointHistory == null) {
            return null;
        }
        return PointHistoryDto.builder()
                              .pointHistoryId(pointHistory.getPointHistoryId())
                              .membershipId(pointHistory.getMembershipId())
                              .storeId(pointHistory.getStoreId())
                              .storeName(pointHistory.getStoreName())
                              .industryType(pointHistory.getIndustryType())
                              .amount(pointHistory.getAmount())
                              .changeReason(pointHistory.getChangeReason())
                              .createdAt(pointHistory.getCreatedAt())
                              .build();
    }
}
