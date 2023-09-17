package flab.docdoc.hospitalSubInfo.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Set;

@Getter
public class HospitalTag {

    private Long hospitalUniqueId;
    private Tag tag;


    @Builder(builderClassName = "HospitalTagBuilder", builderMethodName = "HospitalTagBuilder")
    public HospitalTag(Long hospitalUniqueId, Tag tag) {
        Assert.notNull(tag, "tag must be not null");
        this.hospitalUniqueId = hospitalUniqueId;
        this.tag = tag;
    }


    public static List<HospitalTag> of(Long hospitalUniqueId, Set<Tag> tags) {
        Assert.notNull(hospitalUniqueId, "hospitalUniqueId must be not null");
        return  tags.stream()
                .map((v) -> HospitalTag.HospitalTagBuilder()
                        .hospitalUniqueId(hospitalUniqueId)
                        .tag(v)
                        .build())
                .toList();
    }
}
