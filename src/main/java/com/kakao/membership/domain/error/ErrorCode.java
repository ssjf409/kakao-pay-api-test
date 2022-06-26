package com.kakao.membership.domain.error;

import com.kakao.membership.common.MessageSourceHandler;

public enum ErrorCode {

    UNKNOWN_SYSTEM_ERROR("00000", "unknown.system.error"),
    FAILED_TO_FIND_STORE("01000", "failed.to.find.store"),
    FAILED_TO_SUBTRACT_POINT("02000", "failed.to.subtract_point"),
    FAILED_TO_FIND_MEMBERSHIP("03000", "failed.to.find.membership"),
    FAILED_TO_CHANGE_POINT("04000", "failed.to.change.point"),
    INVALID_INPUT_DATA("05000", "invalid.input.data"),
    ;

    private final String code;
    private final String messageCode;

    ErrorCode(String code, String messageCode) {
        this.code = code;
        this.messageCode = messageCode;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return MessageSourceHandler.getMessageStatic(this.messageCode);
    }

    public String getMessage(Object... params) {
        return MessageSourceHandler.getMessage(this.messageCode, params);
    }

    public String getMessageCode() {
        return messageCode;
    }
}
