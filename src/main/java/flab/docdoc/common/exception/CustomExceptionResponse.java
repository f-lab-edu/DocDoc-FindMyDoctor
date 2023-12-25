package flab.docdoc.common.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomExceptionResponse {

    private CustomErrorCode errorCode;
    private String message;

}
