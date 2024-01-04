package flab.docdoc.hospitalSubInfo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Weekday {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7);

    private final int sequence;

    Weekday(int sequence) {this.sequence = sequence;}

    int getSequence() {return sequence;}

    private static final Map<Integer, Weekday> codeSequenceMap =
            Stream.of(values()).collect(Collectors.toMap(Weekday::getSequence, e -> e));

    @JsonCreator
    public static Weekday ToEnum(int sequence) {
        return codeSequenceMap.get(sequence);
    }
}
