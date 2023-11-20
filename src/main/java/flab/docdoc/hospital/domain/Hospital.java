package flab.docdoc.hospital.domain;


import lombok.*;

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
    private String adminId;

    @Builder
    public Hospital(String uniqueId, String businessName, String sidoNm, String sgguNm , String addr, String tel, String xPos, String yPos, String adminId, Long star, Long reviewCount) {
        this.uniqueId = uniqueId;
        this.businessName = businessName;
        this.sidoNm = sidoNm;
        this.sgguNm = sgguNm;
        this.addr = addr;
        this.tel = tel;
        this.xPos = xPos;
        this.yPos = yPos;
        this.adminId = adminId;
        this.star = star;
        this.reviewCount = reviewCount;
    }


}
