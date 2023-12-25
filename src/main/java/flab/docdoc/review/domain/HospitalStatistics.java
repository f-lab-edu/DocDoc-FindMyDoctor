package flab.docdoc.review.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HospitalStatistics {


    private String hospitalUniqueId;
    private Long star;
    private Long reviewCount;

    @Builder(builderClassName = "HospitalStatisticsBuilder", builderMethodName = "HospitalStatisticsBuilder")
    public HospitalStatistics(String hospitalUniqueId, Long star, Long reviewCount) {
        this.hospitalUniqueId = hospitalUniqueId;
        this.star = star;
        this.reviewCount = reviewCount;
    }

    public static HospitalStatistics toSave(Review review) {
        Assert.notNull(review.getReviewUniqueId(), "reviewUniqueId must not be null");
        Assert.notNull(review.getStar(), "star must not be null");

        return HospitalStatistics.HospitalStatisticsBuilder()
                .hospitalUniqueId(review.getHospitalUniqueId())
                .star(review.getStar())
                .reviewCount(1L)
                .build();
    }

    public static HospitalStatistics toDelete(Review review) {
        Assert.notNull(review.getReviewUniqueId(), "reviewUniqueId must not be null");
        Assert.notNull(review.getStar(), "star must not be null");

        return HospitalStatistics.HospitalStatisticsBuilder()
                .hospitalUniqueId(review.getHospitalUniqueId())
                .star(-review.getStar())
                .reviewCount(-1L)
                .build();
    }

    public static HospitalStatistics toUpdate(Review existReview, Review newReview) {
        //existReview와 newReview의 순서 바뀜 방지용
        Assert.isNull(newReview.getHospitalUniqueId(), "hospitalUniqueId must be null.");
        Assert.isNull(newReview.getMemberUniqueId(), "memberUniqueId must be null.");
        Assert.isNull(newReview.getVisitDate(), "visitDate must be null.");


        Assert.notNull(existReview.getReviewUniqueId(), "reviewUniqueId must not be null");
        Assert.notNull(existReview.getStar(), "existStar must not be null");
        Assert.notNull(newReview.getStar(), "newStar must not be null");

        return HospitalStatistics.HospitalStatisticsBuilder()
                .hospitalUniqueId(existReview.getHospitalUniqueId())
                .star(newReview.getStar() - existReview.getStar())
                .reviewCount(0L)
                .build();
    }
}
