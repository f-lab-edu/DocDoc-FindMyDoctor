package flab.docdoc.member.request;

import flab.docdoc.common.contact.Contact;
import flab.docdoc.member.domain.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddMemberRequest {

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
    @NotEmpty
    @Email(message = "not valid email")
    private String email;

    public static Member of (AddMemberRequest request) {
        return Member.AddMemberBuilder()
                .loginId(request.getLoginId())
                .password(request.getPassword())
                .email(request.getEmail())
                .build();
    }
}
