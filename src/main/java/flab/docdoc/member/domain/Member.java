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

    private Contact contact;

    @Builder(builderClassName = "AddMemberBuilder", builderMethodName = "AddMemberBuilder")
    public Member(String loginId, Role role, String password,String phone, String email) {
        Assert.notNull(loginId, "loginId must not be null");
        Assert.notNull(password, "password must not be null");

        this.loginId = loginId;
        this.role = role;
        this.password = password;
        this.contact = Contact.builder()
                .phone(phone)
                .email(email)
                .build();
    }


}
