package com.kakao.membership.domain.entity.store;

import com.kakao.membership.domain.LengthConstant;
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
import javax.persistence.Table;


@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Store extends BaseEntity {

    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long storeId;

    @Column
    private String storeName;

    @Enumerated(EnumType.STRING)
    @Column(length = LengthConstant.INDUSTRY_TYPE)
    private IndustryType industryType;
}
