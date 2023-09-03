package flab.docdoc.hospital.domain;

import flab.docdoc.common.contact.Contact;
import flab.docdoc.common.location.domain.Location;
import lombok.*;
import org.springframework.util.Assert;

import java.util.Set;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hospital {
    private Long uniqueId;
    private String name;
    private Contact contact;
    private Location location;
    private String detailLocation;
    private Set<Subject> subjects;
    private Long adminId;

    @Builder(builderClassName = "AddHospitalBuilder", builderMethodName = "AddHospitalBuilder")
    public Hospital(String name, String phone, String email, Location location, String detailLocation, Set<Subject> subjects) {
        Assert.notNull(name, "name must not be null");
        Assert.notNull(phone, "phone must not be null");
        Assert.notNull(email, "email must not be null");
        Assert.notNull(location.getName(), "location name must not be null");
        Assert.notNull(location.getParentLocationName(), "parent location name must not be null");
        Assert.notNull(detailLocation, "detail location must not be null");
        Assert.notEmpty(subjects, "subject must not be empty");

        this.uniqueId = uniqueId;
        this.name = name;
        this.contact = Contact.builder().phone(phone).email(email).build();
        this.location = location;
        this.detailLocation = detailLocation;
        this.subjects = subjects;
    }

    @Builder(builderClassName = "UpdateHospitalBuilder", builderMethodName = "UpdateHospitalBuilder")
    public Hospital(Long uniqueId, String name, String phone, String email, Location location, String detailLocation, Set<Subject> subjects, Long adminId) {
        Assert.notNull(name, "uniqueId must not be null");
        Assert.notNull(name, "name must not be null");
        Assert.notNull(phone, "phone must not be null");
        Assert.notNull(email, "email must not be null");
        Assert.notNull(location.getName(), "location name must not be null");
        Assert.notNull(location.getParentLocationName(), "parent location name must not be null");
        Assert.notNull(detailLocation, "detail location must not be null");
        Assert.notEmpty(subjects, "subject must not be empty");

        this.uniqueId = uniqueId;
        this.name = name;
        this.contact = Contact.builder().phone(phone).email(email).build();
        this.location = location;
        this.detailLocation = detailLocation;
        this.subjects = subjects;
        this.adminId = adminId;
    }


}
