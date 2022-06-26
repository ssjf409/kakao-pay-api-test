package com.kakao.membership.service.membership;

import com.kakao.membership.converter.MembershipDtoConverter;
import com.kakao.membership.domain.entity.membership.Membership;
import com.kakao.membership.repository.member.MembershipRepository;
import com.kakao.membership.service.dto.MembershipDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@RequiredArgsConstructor
@Service
public class MembershipService {
    
    private final static long RANDOM_BARCODE_MIN = 1000000000L;
    private final static long RANDOM_BARCODE_MAX = 9999999999L;
    private final static long RANDOM_BARCODE_RANGE = RANDOM_BARCODE_MAX - RANDOM_BARCODE_MIN;

    private final MembershipRepository membershipRepository;

    @Transactional
    public MembershipDto findOrCreate(Long userId) {
        Membership membership = membershipRepository.findMembershipByUserId(userId);
        if (membership == null) {
            membership = Membership.builder()
                                   .userId(userId)
                                   .build();

            String barcode = createUniqueBarcode();
            membership.setBarcode(barcode);
            membership = membershipRepository.save(membership);
        }
        return MembershipDtoConverter.convert(membership);
    }

    private String createUniqueBarcode() {
        while (true) {
            String barcode = createRandomBarcode();
            if (!membershipRepository.existsByBarcode(barcode)) {
                return barcode;
            }
        }
    }

    private String createRandomBarcode() {
        Random random = new Random();
        long generatedBarcode = (long) (RANDOM_BARCODE_RANGE * random.nextDouble()) + RANDOM_BARCODE_MIN;
        return String.valueOf(generatedBarcode);
    }

    @Transactional(readOnly = true)
    public MembershipDto findByBarcode(String barcode) {
        if (barcode == null) {
            return null;
        }
        return MembershipDtoConverter.convert(membershipRepository.findMembershipByBarcode(barcode));
    }
}
