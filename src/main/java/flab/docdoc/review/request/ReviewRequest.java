package flab.docdoc.review.request;

import flab.docdoc.review.domain.Review;
import flab.docdoc.review.domain.Star;
import lombok.Getter;
import org.springframework.util.Assert;

import java.util.Date;

@Getter
public class ReviewRequest {

    private Long reviewUniqueId;
    private String hospitalUniqueId;
    private Star star;
    private String content;
    private Date visitDate;

    public static Review toNewReview(ReviewRequest request, final String loginId) {
        Assert.isNull(request.getReviewUniqueId(), "new review's id must be null");
        Assert.notNull(request.getHospitalUniqueId(), "hospitalUniqueId must not be null");
        Assert.notNull(request.getHospitalUniqueId(), "memberUniqueId must not be null");
        Assert.notNull(request.getHospitalUniqueId(), "star must not be null");
        Assert.notNull(request.getHospitalUniqueId(), "content must not be null");
        Assert.notNull(request.getHospitalUniqueId(), "visitDate must not be null");
        return Review.builder()
                .hospitalUniqueId(request.getHospitalUniqueId())
                .writer(loginId)
                .star(request.getStar())
                .content(request.getContent())
                .visitDate(request.getVisitDate())
                .build();
    }

    public static Review toUpdateBuilder(ReviewRequest request, Review existReview, final String loginId) {
        //업데이트 리뷰 dto에 들어가야만 하는 내용
        Assert.notNull(request.getReviewUniqueId(), "reviewUniqueId must not be null");
        Assert.notNull(request.getStar(), "star must not be null");
        Assert.notNull(request.getContent(), "content must not be null");

        //업데이트 리뷰 dto에 들어가면 안 될 내용 -> 변경되면 안될 내용
        Assert.isNull(request.getHospitalUniqueId(), "hospitalUniqueId must be null");
        Assert.notNull(loginId, "loginId must be null");
        Assert.isNull(request.getVisitDate(), "visitDate must be null");

        //기존 리뷰에 들어가야할 내용
        Assert.notNull(existReview.getReviewUniqueId(), "reviewUniqueId must not be null");
        Assert.notNull(existReview.getHospitalUniqueId(), "hospitalUniqueId must not be null");
        Assert.notNull(existReview.getWriter(), "writer must not be null");
        Assert.notNull(existReview.getStar(), "star must not be null");
        Assert.notNull(existReview.getContent(), "content must not be null");
        Assert.notNull(existReview.getVisitDate(), "visitDate must not be null");

        return Review.builder()
                .reviewUniqueId(existReview.getReviewUniqueId())
                .star(request.getStar())
                .content(request.getContent())
                .build();
    }
}
