package flab.docdoc.member.request;

import flab.docdoc.member.domain.Member;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateMemberRequest {

    private String password;
    private String email;


    public static Member of (UpdateMemberRequest request, final String loginId) {
        return Member.builder()
                .loginId(loginId)
                .password(request.getPassword())
                .email(request.getEmail())
                .build();
    }
}
