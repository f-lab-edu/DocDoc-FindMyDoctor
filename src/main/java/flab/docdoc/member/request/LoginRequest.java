package flab.docdoc.member.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginRequest {


    @NotNull
    private final String loginId;
    @NotNull
    private final String password;


}
