package flab.docdoc.hospital.response;

import flab.docdoc.hospital.domain.Hospital;
import flab.docdoc.hospitalSubInfo.domain.HospitalSubject;
import flab.docdoc.hospitalSubInfo.domain.SubInfo;
import flab.docdoc.hospitalSubInfo.domain.Subject;
import flab.docdoc.member.domain.Member;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HospitalResponse {

    private String businessName;
    private String tel;
    private String sidoNm;
    private String sgguNm;
    private String addr;
    private String xPos; //XPos
    private String yPos; //YPos

    private String adminLoginId;
    private String adminEmail;

    private List<String> subjects;



    @Builder
    public HospitalResponse(HospitalResponse response, List<Subject> subjects) {
        this.businessName = response.getBusinessName();
        this.tel = response.getTel();
        this.sidoNm = response.getSidoNm();
        this.sgguNm = response.getSgguNm();
        this.addr = response.getAddr();
        this.xPos = response.getXPos();
        this.yPos = response.getYPos();
        this.adminLoginId = response.getAdminLoginId();
        this.adminEmail = response.getAdminEmail();

        this.subjects = subjects.stream()
                    .map(Subject::getOpenLable)
                    .toList();
    }

    public static HospitalResponse of (HospitalResponse response, SubInfo subInfo) {
        return HospitalResponse.builder()
                .response(response)
                .subjects(subInfo.getSubjects())
                .build();
    }

}
