package flab.docdoc.hospital.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Subject {
    FM("가정의학과"),
    IM("내과"),
    CP("병리과"),
    URO("비뇨의학과"),
    OBGY("산부인과"),
    PS("성형외과"),
    PED("소아청소년과"),
    NS("신경외과"),
    OPH("안과"),
    GS("외과"),
    EM("응급의학과"),
    ENT("이비인후과"),
    RM("재활의학과"),
    PSY("정신건강의학과"),
    OS("정형외과"),
    DT("치과"),
    DER("피부과"),
    TKM("한의원");

    private final String lable;

    Subject(String lable) {
        this.lable = lable;
    }

    private String getLable() {
        return this.lable;
    }


    private static final Map<String, Subject> findSubject =
            Stream.of(values()).collect(Collectors.toMap(Subject::getLable, e -> e));

    @JsonCreator
    public static Subject ToEnum(String lable) {
        return Optional.ofNullable(findSubject.get(lable)).orElseThrow(() -> new IllegalStateException("진료과목이 없습니다."));
    }



}
