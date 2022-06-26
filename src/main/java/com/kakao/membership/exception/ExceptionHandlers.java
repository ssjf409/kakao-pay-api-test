package com.kakao.membership.exception;

import com.kakao.membership.common.LocaleHolder;
import com.kakao.membership.common.MessageSourceHandler;
import com.kakao.membership.domain.error.ErrorCode;
import com.kakao.membership.domain.response.Response;
import com.kakao.membership.domain.response.Response.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlers {

    private final MessageSource messageSource;

    public ExceptionHandlers(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String messageKey = null;
        FieldError fieldError = e.getFieldError();
        if (fieldError != null) {
            messageKey = fieldError.getDefaultMessage();
        }
        return new ResponseEntity<>(Response.error(
                Error.of(ErrorCode.INVALID_INPUT_DATA.toString(),
                         MessageSourceHandler.getMessageStatic(messageKey))), HttpStatus.OK);
    }

    @ExceptionHandler(NotFoundMembershipException.class)
    public ResponseEntity<Response> handleNotFoundMembershipException(NotFoundMembershipException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(Response.error(toError(ErrorCode.FAILED_TO_FIND_MEMBERSHIP)), HttpStatus.OK);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Response> handleBaseException(BaseException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(Response.error(toError(ErrorCode.UNKNOWN_SYSTEM_ERROR)), HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleBaseException(Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(Response.error(toError(ErrorCode.UNKNOWN_SYSTEM_ERROR)), HttpStatus.OK);
    }

    private Error toError(ErrorCode errorCode) {
        try {
            String message = messageSource.getMessage(errorCode.getMessageCode(), null, LocaleHolder.getLocale());
            return Response.Error.of(errorCode.getCode(), message);
        } catch (NoSuchMessageException e) {
            String message = messageSource
                    .getMessage(ErrorCode.UNKNOWN_SYSTEM_ERROR.getCode(), null, LocaleHolder.getLocale());
            return Response.Error.of(errorCode.getCode(), message);
        }
    }
}
