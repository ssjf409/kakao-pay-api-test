package com.kakao.membership.repository.point;

import com.kakao.membership.domain.entity.point.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {

    List<PointHistory> findAllByMembershipIdAndCreatedAtBetween(Long membershipId, LocalDateTime startDate,
                                                                LocalDateTime endDate);
}
