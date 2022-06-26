package com.kakao.membership.repository.member;


import com.kakao.membership.domain.entity.membership.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRepository extends JpaRepository<Membership, Long> {

    Membership findMembershipByUserId(Long userId);

    boolean existsByBarcode(String barcode);

    Membership findMembershipByBarcode(String barcode);
}
