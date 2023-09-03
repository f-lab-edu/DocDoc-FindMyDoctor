package flab.docdoc.common.location.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Location {
    private String name;
    private String parentLocationName;
}
