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
public class StoreDto {
    private Long storeId;
    private String storeName;
    private IndustryType industryType;
}
