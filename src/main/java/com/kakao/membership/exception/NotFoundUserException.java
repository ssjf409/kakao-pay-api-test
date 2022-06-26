package com.kakao.membership.exception;

public class NotFoundUserException extends BaseException {

    public NotFoundUserException() {
        super();
    }

    public NotFoundUserException(String message) {
        super(message);
    }

    public NotFoundUserException(String errorCode, String message) {
        super(errorCode, message);
    }
}
