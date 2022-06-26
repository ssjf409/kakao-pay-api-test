package com.kakao.membership.domain.entity.membership;

import com.kakao.membership.domain.entity.audit.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(indexes = @Index(name = "membership_idx01", columnList = "barcode, deleted"))
public class Membership extends BaseEntity {

    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long membershipId;

    @Column(unique = true)
    private Long userId;

    @Column(unique = true)
    private String barcode;
}
