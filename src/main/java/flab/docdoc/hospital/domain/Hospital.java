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
//
//    @Builder(builderClassName = "UpdateHospitalBuilder", builderMethodName = "UpdateHospitalBuilder")
//    public Hospital(Long uniqueId, String name, String phone, String email, Location location, String detailLocation, Set<Subject> subjects, Long adminId) {
//        Assert.notNull(name, "uniqueId must not be null");
//        Assert.notNull(name, "name must not be null");
//        Assert.notNull(phone, "phone must not be null");
//        Assert.notNull(email, "email must not be null");
//        Assert.notNull(location.getName(), "location name must not be null");
//        Assert.notNull(location.getParentLocationName(), "parent location name must not be null");
//        Assert.notNull(detailLocation, "detail location must not be null");
//        Assert.notEmpty(subjects, "subject must not be empty");
//
//        this.uniqueId = uniqueId;
//        this.name = name;
//        this.contact = Contact.builder().phone(phone).email(email).build();
//        this.location = location;
//        this.detailLocation = detailLocation;
//        this.subjects = subjects;
//        this.adminId = adminId;
//    }


}
