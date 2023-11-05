package flab.docdoc.member.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    public enum Role {
        PUBLIC,
        ADMIN
    }

    private Long uniqueId;

    private Role role = Role.PUBLIC;

    private String loginId;

    private String password;

    private String email;



    @Builder
    public Member(String loginId, String password, String email) {
        Assert.notNull(loginId, "loginId must not be null");
        Assert.notNull(password, "password must not be null");
        this.loginId = loginId;
        this.password = password;
        this.email = email;
    }


}
