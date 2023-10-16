package flab.docdoc.member.response;


import flab.docdoc.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponse {

    private Member.Role role;

    private String loginId;

    private String email;


    @Builder
    public MemberResponse(Member.Role role, String loginId, String email) {
        this.role = role;
        this.loginId = loginId;
        this.email = email;
    }

    public static MemberResponse of(Member member) {
        return MemberResponse.builder()
                .role(member.getRole())
                .loginId(member.getLoginId())
                .email(member.getEmail())
                .build();
    }

}
