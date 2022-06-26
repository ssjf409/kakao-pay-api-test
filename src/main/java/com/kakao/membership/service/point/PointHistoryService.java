package com.kakao.membership.service.point;

import com.kakao.membership.converter.PointHistoryDtoConverter;
import com.kakao.membership.domain.entity.point.PointHistory;
import com.kakao.membership.domain.entity.store.IndustryType;
import com.kakao.membership.repository.point.PointHistoryRepository;
import com.kakao.membership.service.dto.PointHistoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.kakao.membership.domain.entity.point.changeReason.*;


@RequiredArgsConstructor
@Component
public class PointHistoryService {

    private final static Long CALCULATION_CRITERIA = 0L;
    private final PointHistoryRepository pointHistoryRepository;

    @Transactional
    public void save(Long membershipId, Long storeId, IndustryType industryType, String storeName, Long amount) {
        pointHistoryRepository.save(createPointHistory(membershipId, storeId, industryType, storeName, amount));
    }

    private PointHistory createPointHistory(Long membershipId, Long storeId, IndustryType industryType,
                                            String storeName, Long amount) {
        return PointHistory.builder()
                           .membershipId(membershipId)
                           .storeId(storeId)
                           .storeName(storeName)
                           .industryType(String.valueOf(industryType))
                           .amount(amount)
                           .changeReason(amount < CALCULATION_CRITERIA? USE : EARN)
                           .build();
    }

    @Transactional(readOnly = true)
    public List<PointHistoryDto> searchHistory(Long membershipId, LocalDateTime startDate, LocalDateTime endDate) {
        if (Objects.isNull(startDate) || Objects.isNull(endDate) || startDate.isAfter(endDate)) {
            return Collections.emptyList();
        }

        return PointHistoryDtoConverter.convert(
                pointHistoryRepository.findAllByMembershipIdAndCreatedAtBetween(membershipId, startDate, endDate));
    }
}
