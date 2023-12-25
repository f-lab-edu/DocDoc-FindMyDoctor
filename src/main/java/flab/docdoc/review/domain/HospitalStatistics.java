package flab.docdoc.review.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HospitalStatistics {

    @NotNull
    private String hospitalUniqueId;
    private int star;
    @NotNull
    private int reviewCount;

}
