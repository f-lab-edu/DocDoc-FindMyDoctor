package flab.docdoc.hospital.request;

import flab.docdoc.hospital.domain.Hospital;
import flab.docdoc.hospitalSubInfo.domain.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
@Builder
public class UpdateHospitalRequest {

    @NotNull
    private Long uniqueId;

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

    private List<Workday> workdays;

    private Set<Holiday> holidays;

    private Set<Tag> tags;


    public static Hospital of(UpdateHospitalRequest request) {
        return Hospital.UpdateOpenInfo()
                .uniqueId(request.getUniqueId())
                .businessName(request.getBusinessName())
                .destrict(request.getDestrict())
                .city(request.getCity())
                .detailLocation(request.getDetailLocation())
                .build();
    }

}
