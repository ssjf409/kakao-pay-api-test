package com.kakao.membership.application.dto;

import com.kakao.membership.domain.entity.store.IndustryType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PointChange {
    private Long membershipId;
    private String storeName;
    private IndustryType industryType;
    private Long changeAmount;
}
