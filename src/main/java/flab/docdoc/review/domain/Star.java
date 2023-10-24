package flab.docdoc.review.domain;

import lombok.Getter;

@Getter
public enum Star {

    ONE(1L),
    TWO(2L),
    THREE(3L),
    FOUR(4L),
    FIVE(5L);

    private Long value;

    Star(Long value) {
        this.value = value;
    }



}
