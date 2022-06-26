package com.kakao.membership.exception;

import com.kakao.membership.domain.error.ErrorCode;
import lombok.Getter;

public class NotFoundStoreException extends BaseException {

    @Getter
    private final ErrorCode code = ErrorCode.FAILED_TO_FIND_STORE;

    public NotFoundStoreException() {
        super();
    }

    public NotFoundStoreException(String message) {
        super(message);
    }

    public NotFoundStoreException(String errorCode, String message) {
        super(errorCode, message);
    }
}
