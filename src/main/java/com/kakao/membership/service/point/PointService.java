package com.kakao.membership.service.point;

import com.kakao.membership.converter.PointDtoConverter;
import com.kakao.membership.domain.entity.point.Point;
import com.kakao.membership.domain.entity.store.IndustryType;
import com.kakao.membership.exception.NegativePointException;
import com.kakao.membership.repository.point.PointRepository;
import com.kakao.membership.service.dto.PointDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class PointService {

    private final static Long LONG_ZERO = 0L;
    private final PointRepository pointRepository;
    private final PointHistoryService pointHistoryService;

    @Transactional
    public PointDto upsert(Long membershipId, Long storeId, IndustryType industryType, String storeName, Long amount) {
        if (membershipId == null || storeId == null || industryType == null || amount == null) {
            return null;
        }

        Point point = pointRepository.findPointByMembershipIdAndIndustryType(membershipId, industryType);
        if (point == null) {
            point = Point.builder()
                         .membershipId(membershipId)
                         .industryType(industryType)
                         .amount(amount)
                         .build();

        } else {
            Long newIndustryPoint = point.getAmount() + amount;

            if (newIndustryPoint < LONG_ZERO) {
                throw new NegativePointException();
            }

            point.setAmount(newIndustryPoint);
        }

        Point savedPoint = pointRepository.save(point);
        pointHistoryService.save(membershipId, storeId, industryType, storeName, amount);

        log.info("success to change point, membershipId: {}, storeId: {}, industryType: {}, storeName: {}, amount: {}",
                 membershipId, storeId, industryType, storeName, amount);
        return PointDtoConverter.convert(savedPoint);
    }


}
