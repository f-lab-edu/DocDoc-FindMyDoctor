package flab.docdoc.review.request;

import flab.docdoc.common.aop.CheckWriter.WriterMatch;
import flab.docdoc.common.exception.CustomErrorCode;
import flab.docdoc.common.exception.CustomException;
import flab.docdoc.review.domain.Star;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import java.util.Date;

@Getter
@Builder
public class AddReviewRequest implements WriterMatch {


    private Long reviewUniqueId;
    @NotEmpty
    private String hospitalUniqueId;
    @NotEmpty
    private String writer;
    @NotNull
    private Star star;
    @NotEmpty
    private String content;
    @NotNull
    private Date visitDate;


    @Override
    public void checkWriter(final String loginId) {
        if (!this.writer.equals(loginId)) {
            throw new CustomException(CustomErrorCode.NOT_MATCHED_WRITER);
        }
    }
}
