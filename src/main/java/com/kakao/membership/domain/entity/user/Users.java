package com.kakao.membership.domain.entity.user;

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
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import static com.kakao.membership.domain.LengthConstant.*;


@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(indexes = @Index(name = "users_idx01", columnList = "loginId, hashedPassword, deleted"))
public class Users extends BaseEntity {

    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long userId;

    @Size(max = EMAIL)
    @Column(unique = true)
    private String loginId;

    @Size(max = PASSWORD)
    @Column
    private String hashedPassword;

    @Size(max = EMAIL)
    @Column
    private String email;

    @Size(max = NAME)
    @Column
    private String username;

    //    @Column(columnDefinition = "TINYINT(1)", length = 1)
    @Column(length = 1)
    private boolean dormant;

    @Column
    private LocalDateTime dormantAt;

    //    @Column(columnDefinition = "TINYINT(1)", length = 1)
    @Column(length = 1)
    private boolean withdrawn;

    @Column
    private LocalDateTime withdrawnAt;
}
