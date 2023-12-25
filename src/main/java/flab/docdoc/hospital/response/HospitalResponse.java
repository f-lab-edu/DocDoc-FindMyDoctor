package flab.docdoc.hospital.response;


import flab.docdoc.hospitalSubInfo.domain.SubInfo;
import flab.docdoc.hospitalSubInfo.domain.Subject;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


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

    private String adminId;
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
        this.adminId = response.getAdminId();
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
