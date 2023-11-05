package flab.docdoc.hospitalSubInfo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Tag {
    PARKING("주차가능"),
    FEMALE_DOCTOR("여의사진료"),
    SPECIALITY("전문의"),
    NIGHT("야간진료"),
    DOCDOC_RECOMMEND("똑똑추천");

    private final String lable;

    Tag(String lable) {
        this.lable = lable;
    }

    private String getLable() {
        return this.lable;
    }


    private static final Map<String, Tag> codeLableMap =
            Stream.of(values()).collect(Collectors.toMap(Tag::getLable, e -> e));

    @JsonCreator
    public static Tag ToEnum(String lable) {
        return Optional.ofNullable(codeLableMap.get(lable)).orElseThrow(() -> new IllegalStateException("존재하지 않는 태그 입니다."));
    }



}
