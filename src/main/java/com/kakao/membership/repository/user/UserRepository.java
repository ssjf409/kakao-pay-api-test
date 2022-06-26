package com.kakao.membership.repository.user;

import com.kakao.membership.domain.entity.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByUserId(Long userId);
}
