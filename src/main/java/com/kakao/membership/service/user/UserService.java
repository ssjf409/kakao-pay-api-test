package com.kakao.membership.service.user;

import com.kakao.membership.converter.UserDtoConverter;
import com.kakao.membership.repository.user.UserRepository;
import com.kakao.membership.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserDto findUser(Long userId) {
        if (userId == null) {
            return null;
        }
        return UserDtoConverter.convert(userRepository.findByUserId(userId));
    }
}
