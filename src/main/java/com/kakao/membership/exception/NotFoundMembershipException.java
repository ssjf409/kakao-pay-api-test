package com.kakao.membership.exception;

import com.kakao.membership.domain.error.ErrorCode;
import lombok.Getter;

public class NotFoundMembershipException extends BaseException {

    @Getter
    private final ErrorCode code = ErrorCode.FAILED_TO_FIND_MEMBERSHIP;

    public NotFoundMembershipException() {
        super();
    }

    public NotFoundMembershipException(String message) {
        super(message);
    }

    public NotFoundMembershipException(String errorCode, String message) {
        super(errorCode, message);
    }
}
