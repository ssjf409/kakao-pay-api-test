package com.kakao.membership.service.dto;

import com.kakao.membership.domain.entity.point.changeReason;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointHistoryDto {

    private Long pointHistoryId;
    private Long membershipId;
    private Long storeId;
    private String storeName;
    private String industryType;
    private Long amount;
    private changeReason changeReason;
    private LocalDateTime createdAt;
}
