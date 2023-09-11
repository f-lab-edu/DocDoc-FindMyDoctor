package flab.docdoc.member.domain;

import flab.docdoc.common.contact.Contact;
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
        HOSPITAL_ADMIN,
        ADMIN
    }

    private Long uniqueId;

    private Role role = Role.PUBLIC;

    private String loginId;

    private String password;

    private String email;



    @Builder(builderClassName = "AddMemberBuilder", builderMethodName = "AddMemberBuilder")
    public Member(String loginId, String password, String email) {
        Assert.notNull(loginId, "loginId must not be null");
        Assert.notNull(password, "password must not be null");
        this.loginId = loginId;
        this.password = password;
        this.email = email;
    }

    @Builder(builderClassName = "UpdateMemberBuilder", builderMethodName = "UpdateMemberBuilder")
    public Member(Long uniqueId,  String password, String email) {
        Assert.notNull(uniqueId, "uniqueId must not be null");
        Assert.notNull(password, "password must not be null");
        this.uniqueId = uniqueId;
        this.password = password;
        this.email = email;
    }

    @Builder(builderClassName = "UpdateMemberBuilder", builderMethodName = "UpdateMemberRole")
    private Member(Long uniqueId, Role role) {
        Assert.notNull(uniqueId, "uniqueId must not be null");
        this.uniqueId = uniqueId;
        this.role = role;
    }

}
