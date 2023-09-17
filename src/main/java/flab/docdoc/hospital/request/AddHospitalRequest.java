package flab.docdoc.hospital.request;

import flab.docdoc.common.vo.location.domain.Location;
import flab.docdoc.hospital.domain.Hospital;
import flab.docdoc.hospitalSubInfo.domain.Subject;
import flab.docdoc.hospitalSubInfo.domain.Contact;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
@Builder
public class AddHospitalRequest {
    @NotBlank
    private String businessName;

    private List<Contact> contacts;

    @NotBlank
    private String destrict;

    @NotBlank
    private String city;

    @NotBlank
    private String detailLocation;

    @NotEmpty
    private Set<Subject> subjects;


    public static Hospital of(AddHospitalRequest request) {
        return Hospital.AddHospitalBuilder()
                .businessName(request.getBusinessName())
                .destrict(request.getDestrict())
                .city(request.getCity())
                .detailLocation(request.getDetailLocation())
                .build();
    }
}
