package flab.docdoc.review.request;

import flab.docdoc.common.aop.CheckWriter.WriterMatch;
import flab.docdoc.common.exception.CustomErrorCode;
import flab.docdoc.common.exception.CustomException;
import flab.docdoc.review.domain.Review;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeleteReviewRequest implements WriterMatch {
    @NotNull
    private Long reviewUniqueId;
    @NotEmpty
    private String hospitalUniqueId;
    @NotEmpty
    private String writer;

    public Review toDomain() {
        return Review.builder()
                .reviewUniqueId(this.reviewUniqueId)
                .hospitalUniqueId(this.hospitalUniqueId)
                .writer(this.writer)
                .build();
    }

    @Override
    public void checkWriter(final String loginId) {
        if (!this.writer.equals(loginId)) {
            throw new CustomException(CustomErrorCode.NOT_MATCHED_WRITER);
        }
    }
}



