package com.kakao.membership.domain.response;


import com.kakao.membership.domain.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {

    private T data;
    private Error error;

    public static <T> Response<T> ok(T data) {
        return new Response<>(data, null);
    }

    public static <T> Response<T> error(Error code) {
        return error(code, null);
    }

    public static <T> Response<T> error(Error code, T data) {
        return new Response<>(data, code);
    }

    public static <T> Response<T> errorCode(ErrorCode code) {
        Error error = new Error(code.getCode(), code.getMessageCode());
        return new Response<>(null, error);
    }

    public static <T> Response<T> errorCode(ErrorCode code, Object... params) {
        Error error = new Error(code.getCode(), code.getMessage(params));
        return new Response<>(null, error);
    }

    @Data
    @NoArgsConstructor
    public static class Error {

        private String code;
        private String message;

        public Error(String code) {
            this.code = code;
        }

        public Error(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public static Error of(String code, String message) {
            return new Error(code, message);
        }

        @Override
        public String toString() {
            return "Error{" +
                   "code='" + code + '\'' +
                   ", description='" + message + '\'' +
                   '}';
        }
    }
}
