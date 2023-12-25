package flab.docdoc.review.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    private Long reviewUniqueId;
    private String hospitalUniqueId;
    private Long memberUniqueId;
    private Long star;
    private String content;
    private Date visitDate;

    @Builder(builderClassName = "ReviewBuilder", builderMethodName = "ReviewBuilder")
    public Review(Long reviewUniqueId, String hospitalUniqueId, Long memberUniqueId, Star star, String content, Date visitDate) {
        this.reviewUniqueId = reviewUniqueId;
        this.hospitalUniqueId = hospitalUniqueId;
        this.memberUniqueId = memberUniqueId;
        this.star = star.getValue();
        this.content = content;
        this.visitDate = visitDate;
    }

}
