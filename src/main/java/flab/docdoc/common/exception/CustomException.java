package flab.docdoc.common.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{

    private CustomErrorCode errorCode;
    private String message;

    public CustomException(CustomErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }
}
