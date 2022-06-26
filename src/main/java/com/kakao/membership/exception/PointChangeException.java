package com.kakao.membership.exception;

import com.kakao.membership.domain.error.ErrorCode;
import lombok.Getter;

public class PointChangeException extends BaseException {

    @Getter
    private final ErrorCode code = ErrorCode.FAILED_TO_CHANGE_POINT;

    public PointChangeException() {
        super();
    }

    public PointChangeException(String message) {
        super(message);
    }

    public PointChangeException(String errorCode, String message) {
        super(errorCode, message);
    }
}
