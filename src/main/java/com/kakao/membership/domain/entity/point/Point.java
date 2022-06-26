package com.kakao.membership.domain.entity.point;

import com.kakao.membership.domain.entity.audit.BaseEntity;
import com.kakao.membership.domain.entity.store.IndustryType;
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
@Table(indexes = @Index(name = "point_idx01", columnList = "membershipId, industryType, deleted"))
public class Point extends BaseEntity {

    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long pointId;

    @Column
    private Long membershipId;

    @Enumerated(EnumType.STRING)
    @Column
    private IndustryType industryType;

    @Column
    private Long amount;
}
