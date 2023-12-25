package flab.docdoc.review.service;

import flab.docdoc.review.domain.Review;
import flab.docdoc.review.request.AddReviewRequest;
import flab.docdoc.review.request.DeleteReviewRequest;
import flab.docdoc.review.request.UpdateReviewRequest;

import java.util.Optional;

public interface ReviewServiceV2 {

    public boolean save(AddReviewRequest request);
    public boolean update(UpdateReviewRequest request);
    public boolean delete(DeleteReviewRequest request);

    public Optional<Review> findByUniqueId(Long reviewUniqueId);


}
