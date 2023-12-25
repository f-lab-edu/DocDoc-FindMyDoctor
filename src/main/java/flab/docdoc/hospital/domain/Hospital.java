package flab.docdoc.hospital.domain;


import lombok.*;
import org.springframework.util.Assert;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hospital {
    private String uniqueId; //ykiho -- pk
    private String businessName; //yadmNm
    private String sidoNm; //sidoCdNm
    private String sgguNm; //sgguCdNm
    private String addr; //addr
    private String tel; //telno
    private String xPos; //XPos
    private String yPos; //YPos
    private Long star;
    private Long reviewCount;

    private Long adminUniqueId;

    @Builder(builderClassName = "AddHospitalBuilder", builderMethodName = "AddHospitalBuilder")
    public Hospital(String uniqueId, String businessName, String sidoNm, String sgguNm , String addr, String tel, String xPos, String yPos) {
        Assert.notNull(uniqueId, "uniqueId must not be null");
        Assert.notNull(businessName, "businessName must not be null");
        Assert.notNull(sidoNm, "sidoNm must not be null");
        Assert.notNull(sgguNm, "sgguNm must not be null");
        Assert.notNull(addr, "addr must not be null");
        this.uniqueId = uniqueId;
        this.businessName = businessName;
        this.sidoNm = sidoNm;
        this.sgguNm = sgguNm;
        this.addr = addr;
        this.tel = tel;
        this.xPos = xPos;
        this.yPos = yPos;
        this.star = 0L;
        this.reviewCount = 0L;
    }

    @Builder(builderClassName = "UpdateHospitalRequest", builderMethodName = "UpdateHospitalBuilder")
    public Hospital(String uniqueId, String businessName, String sidoNm, String sgguNm , String addr, String tel, String xPos, String yPos, Long adminId) {
        Assert.notNull(uniqueId, "uniqueId must not be null");
        Assert.notNull(businessName, "businessName must not be null");
        Assert.notNull(sidoNm, "sidoNm must not be null");
        Assert.notNull(sgguNm, "sgguNm must not be null");
        Assert.notNull(addr, "addr must not be null");
        this.uniqueId = uniqueId;
        this.businessName = businessName;
        this.sidoNm = sidoNm;
        this.sgguNm = sgguNm;
        this.addr = addr;
        this.tel = tel;
        this.xPos = xPos;
        this.yPos = yPos;
        this.adminUniqueId = adminId;
    }


}
