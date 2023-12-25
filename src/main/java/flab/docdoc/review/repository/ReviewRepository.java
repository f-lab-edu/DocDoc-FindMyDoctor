package flab.docdoc.review.repository;

import flab.docdoc.review.domain.Review;
import flab.docdoc.review.request.AddReviewRequest;
import flab.docdoc.review.request.UpdateReviewRequest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ReviewRepository {

    public Review findByUniqueId(final Long reviewUniqueId);

    public int save(AddReviewRequest request);

    public int update(UpdateReviewRequest request);

    public int delete(final Long reviewUniqueId);

}
