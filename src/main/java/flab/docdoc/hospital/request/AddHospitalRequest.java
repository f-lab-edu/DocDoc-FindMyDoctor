package flab.docdoc.hospital.request;

import flab.docdoc.hospital.domain.Hospital;
import flab.docdoc.hospitalSubInfo.domain.Subject;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class AddHospitalRequest {

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


    public static Hospital of(AddHospitalRequest request) {
        return Hospital.AddHospitalBuilder()
                .uniqueId(request.getUniqueId())
                .businessName(request.getBusinessName())
                .tel(request.getTel())
                .sidoNm(request.getSidoNm())
                .sgguNm(request.getSgguNm())
                .addr(request.getAddr())
                .build();
    }
}
