package com.kakao.membership.service.dto;

import com.kakao.membership.domain.entity.store.IndustryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointDto {

    private Long pointId;
    private Long membershipId;
    private IndustryType industryType;
    private Long amount;
}
