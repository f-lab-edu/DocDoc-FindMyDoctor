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
    private String writer;
    private Long star;
    private String content;
    private Date visitDate;

    @Builder
    public Review(Long reviewUniqueId, String hospitalUniqueId, String writer, Star star, String content, Date visitDate) {
        this.reviewUniqueId = reviewUniqueId;
        this.hospitalUniqueId = hospitalUniqueId;
        this.writer = writer;
        this.star = star.getValue();
        this.content = content;
        this.visitDate = visitDate;
    }

}
