package flab.docdoc.common.contact;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
public class Contact {

    private String phone;
    private String email;

}
