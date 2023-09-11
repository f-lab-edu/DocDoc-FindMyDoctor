package flab.docdoc.common.contact;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

@Getter
@Builder
public class Contact {

    private Long memberUniqueId;
    private String phone;
    private String email;

    @Builder(builderClassName = "ContactBuilder", builderMethodName = "ContactBuilder")
    public Contact(Long memberUniqueId, String phone, String email) {
        Assert.notNull(memberUniqueId, "memberUniqueId must not be null");

        this.memberUniqueId = memberUniqueId;
        this.phone = phone;
        this.email = email;
    }

}
