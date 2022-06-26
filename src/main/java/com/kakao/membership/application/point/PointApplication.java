package com.kakao.membership.application.point;

import com.kakao.membership.application.membership.MembershipApplication;
import com.kakao.membership.application.store.StoreApplication;
import com.kakao.membership.controller.dto.membership.PointChangeRequest;
import com.kakao.membership.exception.PointChangeException;
import com.kakao.membership.service.dto.MembershipDto;
import com.kakao.membership.service.dto.PointDto;
import com.kakao.membership.service.dto.PointHistoryDto;
import com.kakao.membership.service.dto.StoreDto;
import com.kakao.membership.service.point.PointHistoryService;
import com.kakao.membership.service.point.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class PointApplication {

    private final StoreApplication storeApplication;
    private final MembershipApplication membershipApplication;
    private final PointService pointService;
    private final PointHistoryService pointHistoryService;

    public PointDto changePoint(PointChangeRequest request) {
        StoreDto store = storeApplication.findStore(request.getStoreId());
        MembershipDto membership = membershipApplication.findMembershipByBarcode(request.getBarcode());
        PointDto upsert = pointService.upsert(membership.getMembershipId(), store.getStoreId(),
                                              store.getIndustryType(), store.getStoreName(), request.getAmount());
        return Optional
                .ofNullable(upsert)
                .orElseThrow(() -> new PointChangeException(String.format(
                        "failed to change point, membershipId: %s, storeId: %s, industryType: %s, storeName: %s, amount: %s",
                        membership.getMembershipId(), store.getStoreId(), store.getIndustryType(), store.getStoreName(),
                        request.getAmount()))
                );
    }

    public List<PointHistoryDto> searchHistory(String barcode, LocalDateTime startDate, LocalDateTime endDate) {
        MembershipDto membership = membershipApplication.findMembershipByBarcode(barcode);
        return pointHistoryService.searchHistory(membership.getMembershipId(), startDate, endDate);
    }
}
