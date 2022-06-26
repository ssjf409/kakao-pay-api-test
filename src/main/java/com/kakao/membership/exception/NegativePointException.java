package com.kakao.membership.exception;

import com.kakao.membership.domain.error.ErrorCode;
import lombok.Getter;

public class NegativePointException extends BaseException {

    @Getter
    private final ErrorCode code = ErrorCode.FAILED_TO_SUBTRACT_POINT;

    public NegativePointException() {
        super();
    }

    public NegativePointException(String message) {
        super(message);
    }

    public NegativePointException(String errorCode, String message) {
        super(errorCode, message);
    }
}
