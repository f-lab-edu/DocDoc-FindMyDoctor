package flab.docdoc.hospital.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HospitalSubject {

    private Long hospitalId;
    private Subject name;

}
