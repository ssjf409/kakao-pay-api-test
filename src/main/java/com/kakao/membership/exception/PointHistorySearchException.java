package com.kakao.membership.exception;

import com.kakao.membership.domain.error.ErrorCode;
import lombok.Getter;

public class PointHistorySearchException extends BaseException {

    @Getter
    private final ErrorCode code = ErrorCode.FAILED_TO_SUBTRACT_POINT;

    public PointHistorySearchException() {
        super();
    }

    public PointHistorySearchException(String message) {
        super(message);
    }

    public PointHistorySearchException(String errorCode, String message) {
        super(errorCode, message);
    }
}
