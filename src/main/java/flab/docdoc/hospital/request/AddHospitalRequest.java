package flab.docdoc.hospital.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import flab.docdoc.common.location.domain.Location;
import flab.docdoc.hospital.domain.Hospital;
import flab.docdoc.hospital.domain.Subject;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class AddHospitalRequest {
    @JsonProperty("name")
    @NotBlank
    private String name;

    @JsonProperty("phone")
    @NotBlank
    private String phone;

    @JsonProperty("email")
    @Email(message = "not valid email")
    private String email;

    @JsonProperty("locationName")
    @NotBlank
    private String locationName;

    @JsonProperty("parentLocationName")
    @NotBlank
    private String parentLocationName;

    @JsonProperty("detailLocation")
    @NotBlank
    private String detailLocation;

    @JsonProperty("subject")
    @NotEmpty
    private Set<Subject> subjects;


    public static Hospital to(AddHospitalRequest request) {
        return Hospital.UpdateHospitalBuilder()
                .name(request.getName())
                .phone(request.getPhone())
                .email(request.getEmail())
                .location(Location.builder()
                        .name(request.getLocationName())
                        .parentLocationName(request.getParentLocationName())
                        .build())
                .detailLocation(request.getDetailLocation())
                .build();
    }
}
