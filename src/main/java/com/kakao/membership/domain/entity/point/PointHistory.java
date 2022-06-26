package com.kakao.membership.domain.entity.point;

import com.kakao.membership.domain.entity.audit.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(indexes = @Index(name = "point_history_idx01", columnList = "membershipId, createdAt, deleted"))
public class PointHistory extends BaseEntity {

    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long pointHistoryId;

    @Column
    private Long membershipId;

    @Column
    private Long storeId;

    @Column
    private String storeName;

    @Column
    private String industryType;

    @Column
    private Long amount;

    @Enumerated(EnumType.STRING)
    @Column
    private changeReason changeReason;
}
