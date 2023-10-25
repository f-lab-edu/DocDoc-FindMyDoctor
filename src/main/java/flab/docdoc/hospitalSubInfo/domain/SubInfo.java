package flab.docdoc.hospitalSubInfo.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class SubInfo {
    private List<Subject> subjects;


    @Builder
    public SubInfo(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public static SubInfo of(List<Subject> subjects) {
        return SubInfo.builder()
                .subjects(subjects)
                .build();
    }
}
