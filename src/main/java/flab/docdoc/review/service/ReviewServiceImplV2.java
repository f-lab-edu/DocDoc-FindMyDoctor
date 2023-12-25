package flab.docdoc.review.service;


import flab.docdoc.review.domain.Review;
import flab.docdoc.review.repository.ReviewRepository;
import flab.docdoc.review.request.AddReviewRequest;
import flab.docdoc.review.request.DeleteReviewRequest;
import flab.docdoc.review.request.UpdateReviewRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ReviewServiceImplV2 implements ReviewServiceV2 {

    private final ReviewRepository reviewRepository;


    public boolean save(AddReviewRequest request) {
        return reviewRepository.save(request) == 1 ? true: false;
    }

    public boolean update(UpdateReviewRequest request) {

        return reviewRepository.update(request) == 1? true : false;
    }

    public boolean delete(DeleteReviewRequest request) {

        return reviewRepository.delete(request.getReviewUniqueId()) == 1? true : false;
    }


    public Optional<Review> findByUniqueId(Long reviewUniqueId) {
        Assert.notNull(reviewUniqueId, "reviewUniqueId must not be null");
        return Optional.ofNullable(reviewRepository.findByUniqueId(reviewUniqueId));
    }



}
