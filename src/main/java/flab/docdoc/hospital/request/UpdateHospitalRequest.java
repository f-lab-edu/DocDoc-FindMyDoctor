package flab.docdoc.hospital.request;

import flab.docdoc.hospital.domain.Hospital;
import flab.docdoc.hospitalSubInfo.domain.*;
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
    private String uniqueId;

    @NotNull
    private String businessName;

    private String tel;

    @NotNull
    private String sidoNm;

    @NotNull
    private String sgguNm;

    @NotNull
    private String addr;

    @NotEmpty
    private Set<Subject> subjects;

    private List<Workday> workdays;

    private Set<Holiday> holidays;

    private Set<Tag> tags;

    public static Hospital of(UpdateHospitalRequest request) {
        return Hospital.builder()
                .uniqueId(request.getUniqueId())
                .businessName(request.getBusinessName())
                .tel(request.getTel())
                .sidoNm(request.getSidoNm())
                .sgguNm(request.getSgguNm())
                .addr(request.getAddr())
                .build();
    }

}
