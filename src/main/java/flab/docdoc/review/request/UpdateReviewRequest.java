package flab.docdoc.review.request;

import flab.docdoc.common.exception.CustomErrorCode;
import flab.docdoc.common.exception.CustomException;
import flab.docdoc.common.aop.CheckWriter.WriterMatch;
import flab.docdoc.review.domain.Star;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateReviewRequest implements WriterMatch {
    @NotNull
    private Long reviewUniqueId;
    @NotEmpty
    private String hospitalUniqueId;
    @NotEmpty
    private String writer;
    @NotNull
    private Star star;
    @NotNull
    private String content;

    @Override
    public void checkWriter(final String loginId) {
        if (!this.writer.equals(loginId)) {
            throw new CustomException(CustomErrorCode.NOT_MATCHED_WRITER);
        }
    }
}



