package com.kakao.membership.repository.point;

import com.kakao.membership.domain.entity.point.Point;
import com.kakao.membership.domain.entity.store.IndustryType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Long> {
    Point findPointByMembershipIdAndIndustryType(Long membershipId, IndustryType industryType);
}
