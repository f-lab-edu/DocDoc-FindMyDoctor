package flab.docdoc.hospital.response;

import flab.docdoc.hospitalSubInfo.domain.SubInfo;
import flab.docdoc.hospitalSubInfo.domain.Subject;
import flab.docdoc.hospitalSubInfo.domain.Workday;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HospitalDetailResponse {

    private String uniqueId;
    private String businessName;
    private String tel;
    private String sidoNm;
    private String sgguNm;
    private String addr;
    private String xPos; //XPos
    private String yPos; //YPos

    private String adminLoginId;
    private String adminEmail;
    private Float hospitalStatistics;


    private List<String> subjects;
    private List<String> holidays;
    private List<Workday> workdays;
    private List<String> tags;




    @Builder
    public HospitalDetailResponse(HospitalDetailResponse response, List<Subject> subjects) {
        this.businessName = response.getBusinessName();
        this.tel = response.getTel();
        this.sidoNm = response.getSidoNm();
        this.sgguNm = response.getSgguNm();
        this.addr = response.getAddr();
        this.xPos = response.getXPos();
        this.yPos = response.getYPos();
        this.adminLoginId = response.getAdminLoginId();
        this.adminEmail = response.getAdminEmail();

//        this.subjects = subjects.stream()
//                    .map(Subject::getOpenLable)
//                    .toList();
    }

    public static HospitalDetailResponse of (HospitalDetailResponse response, SubInfo subInfo) {
        return HospitalDetailResponse.builder()
                .response(response)
                .subjects(subInfo.getSubjects())
                .build();
    }

}
