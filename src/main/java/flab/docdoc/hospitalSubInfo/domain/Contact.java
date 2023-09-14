package flab.docdoc.hospitalSubInfo.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

import java.util.List;

@Getter
@Builder
public class Contact {

    private Long hospitalUniqueId;
    private String phone;
    private String email;

    @Builder(builderClassName = "AddContactBuilder", builderMethodName = "AddContactBuilder")
    public Contact(Long hospitalUniqueId, String phone, String email) {
        this.hospitalUniqueId = hospitalUniqueId;
        this.phone = phone;
        this.email = email;
    }

    public static List<Contact> of(Long hospitalUniqueId, List<Contact> contacts) {
        Assert.notNull(hospitalUniqueId, "hospitalUniqueId must be not null");
        if (contacts.size() == 0 || contacts == null || contacts.size() > 3) throw new IllegalArgumentException("contacts size must not be 0 or bigger than 3");
        return contacts.stream()
                .map(v-> Contact.builder()
                        .hospitalUniqueId(hospitalUniqueId)
                        .phone(v.getPhone())
                        .email(v.getEmail())
                        .build())
                .toList();
    }

}
