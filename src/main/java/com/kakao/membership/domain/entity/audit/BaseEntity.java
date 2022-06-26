package com.kakao.membership.domain.entity.audit;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@SQLDelete(sql = "UPDATE BaseEntity SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
@MappedSuperclass
public abstract class BaseEntity extends AuditEntity {

    //    @Column(columnDefinition = "TINYINT(1)", length = 1)
    @Column(length = 1)
    private boolean deleted;
}