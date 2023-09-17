package flab.docdoc.hospital.domain;


import flab.docdoc.common.vo.location.domain.Location;
import flab.docdoc.hospitalSubInfo.domain.Contact;
import flab.docdoc.hospitalSubInfo.domain.HospitalSubject;
import flab.docdoc.hospitalSubInfo.domain.Subject;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Set;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class Hospital {
    private Long uniqueId;
    private String businessName;
    private String destrict;
    private String city;
    private String detailLocation;
    private Long adminId;

    @Builder(builderClassName = "AddHospitalBuilder", builderMethodName = "AddHospitalBuilder")
    public Hospital(String businessName, String destrict, String city , String detailLocation) {
        Assert.notNull(businessName, "businessName must not be null");
        Assert.notNull(destrict, "destrict must not be null");
        Assert.notNull(city, "city must not be null");
        Assert.notNull(detailLocation, "detail location must not be null");


        this.businessName = businessName;
        this.destrict = destrict;
        this.city = city;
        this.detailLocation = detailLocation;

    }

    @Builder(builderClassName = "UpdateHospitalRequest", builderMethodName = "UpdateOpenInfo")
    public Hospital(Long uniqueId, String businessName, String destrict, String city , String detailLocation) {
        Assert.notNull(businessName, "uniqueId must not be null");
        Assert.notNull(businessName, "businessName must not be null");
        Assert.notNull(destrict, "destrict must not be null");
        Assert.notNull(city, "city must not be null");
        Assert.notNull(detailLocation, "detail location must not be null");

        this.uniqueId = uniqueId;
        this.businessName = businessName;
        this.destrict = destrict;
        this.city = city;
        this.detailLocation = detailLocation;

    }


}
