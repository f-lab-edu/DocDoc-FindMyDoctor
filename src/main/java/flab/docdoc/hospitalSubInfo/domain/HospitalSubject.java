package flab.docdoc.hospitalSubInfo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Set;

@Getter
@Slf4j
public class HospitalSubject {

    private String hospitalUniqueId;
    private Subject subject;


    @Builder(builderClassName = "HospitalSubjectBuilder", builderMethodName = "HospitalSubjectBuilder")
    public HospitalSubject(String hospitalUniqueId, Subject subject) {
        Assert.notNull(subject, "subject must be not null");
        this.hospitalUniqueId = hospitalUniqueId;
        this.subject = subject;
    }


    public static List<HospitalSubject> of(String hospitalUniqueId, Set<Subject> subjects) {
        Assert.notNull(hospitalUniqueId, "hospitalUniqueId must be not null");
        return  subjects.stream()
                .map(v -> HospitalSubject.HospitalSubjectBuilder()
                        .hospitalUniqueId(hospitalUniqueId)
                        .subject(v)
                        .build())
                .toList();
    }

}

