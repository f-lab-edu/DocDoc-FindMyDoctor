package flab.docdoc.review.service;

import flab.docdoc.review.domain.Review;
import flab.docdoc.review.request.AddReviewRequest;
import flab.docdoc.review.request.UpdateReviewRequest;

import java.util.Optional;

public interface ReviewService {

    public void save(AddReviewRequest request, final String loginId);
    public void update(UpdateReviewRequest request, final String loginId);
    public void delete(final Long reviewUniqueId, final String loginId);

    public Optional<Review> findByUniqueId(Long reviewUniqueId);


}
