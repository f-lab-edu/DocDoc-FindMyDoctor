package flab.docdoc.member.request;

import flab.docdoc.member.domain.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateMemberRequest {

    @NotNull
    private Long uniqueId;
    private String password;
    private String email;


    public static Member of (UpdateMemberRequest request) {
        return Member.UpdateMemberBuilder()
                .uniqueId(request.getUniqueId())
                .password(request.getPassword())
                .email(request.getEmail())
                .build();
    }
}
