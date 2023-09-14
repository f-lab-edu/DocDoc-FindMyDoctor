package flab.docdoc.hospitalSubInfo.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Set;

@Getter
@Builder
public class HospitalSubject {

    private Long hospitalUniqueId;
    private Subject subject;

    @Builder(builderClassName = "AddHospitalSubjectBuilder", builderMethodName = "AddHospitalSubjectBuilder")
    public HospitalSubject(Long hospitalUniqueId, Subject subject) {
        Assert.notNull(hospitalUniqueId, "hospitalUniqueId must be not null");
        Assert.notNull(subject, "subject must be not null");

        this.hospitalUniqueId = hospitalUniqueId;
        this.subject = subject;
    }

    public static List<HospitalSubject> of(Long hospitalUniqueId, Set<Subject> subjects) {
        Assert.notNull(hospitalUniqueId, "hospitalUniqueId must be not null");
        Assert.notEmpty(subjects, "subject must not be empty");
        return  subjects.stream()
                .map((v) -> HospitalSubject.builder()
                        .hospitalUniqueId(hospitalUniqueId)
                        .subject(v)
                        .build())
                .toList();
    }

}
