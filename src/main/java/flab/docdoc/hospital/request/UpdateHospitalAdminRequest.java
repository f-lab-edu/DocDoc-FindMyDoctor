package flab.docdoc.hospital.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateHospitalAdminRequest {

    @NotNull
    private String hospitalUniqueId;
}
