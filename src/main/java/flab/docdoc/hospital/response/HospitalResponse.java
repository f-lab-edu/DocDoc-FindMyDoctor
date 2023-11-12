package flab.docdoc.hospital.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Float hospitalStatistics;
    private Integer reviewCount;
    private Boolean isHoliday;
    private Boolean isWorkday;

}
