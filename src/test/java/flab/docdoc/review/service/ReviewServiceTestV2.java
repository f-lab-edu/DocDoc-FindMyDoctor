package flab.docdoc.review.service;

import flab.docdoc.review.domain.Review;
import flab.docdoc.review.domain.Star;
import flab.docdoc.review.repository.ReviewRepository;
import flab.docdoc.review.request.AddReviewRequest;
import flab.docdoc.review.request.DeleteReviewRequest;
import flab.docdoc.review.request.UpdateReviewRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Date;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTestV2 {

    @InjectMocks
    private ReviewServiceImplV2 reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    public static UpdateReviewRequest getUpdateRequest() {
        return UpdateReviewRequest.builder()
                .reviewUniqueId(2L)
                .hospitalUniqueId("A_Hospital")
                .writer("A_Member")
                .star(Star.FIVE)
                .content("좋았어요.")
                .build();
    }

    public static Review getReview() {
        return Review.builder()
                .reviewUniqueId(1L)
                .hospitalUniqueId("A_Hospital")
                .star(5)
                .writer("A_Member")
                .content("좋았어요.")
                .visitDate(new Date(2023, 11, 23))
                .build();
    }

    @Test
    @DisplayName("save 는 성공하면 true 를 반환한다.")
    void save_success_return_true() {
        AddReviewRequest reviewRequest = AddReviewRequest.builder()
                                                        .hospitalUniqueId(null)
                                                        .star(Star.FIVE)
                                                        .content("좋았어요.")
                                                        .visitDate(new Date(2023, 11, 23))
                                                        .build();

        doReturn(1).when(reviewRepository).save(any());

        //when
        boolean saveResult = reviewService.save(reviewRequest);

        //then
        verify(reviewRepository, times(1)).save(any());
        Assertions.assertThat(saveResult).isEqualTo(true);
    }

    @Test
    @DisplayName("save 는 실패하면 false 를 반환한다.")
    void save_fail_return_false() {
        AddReviewRequest reviewRequest = AddReviewRequest.builder()
                .hospitalUniqueId(null)
                .star(Star.FIVE)
                .content("좋았어요.")
                .visitDate(new Date(2023, 11, 23))
                .build();

        doReturn(0).when(reviewRepository).save(any());

        //when
        boolean saveResult = reviewService.save(reviewRequest);

        //then
        verify(reviewRepository, times(1)).save(any());
        Assertions.assertThat(saveResult).isEqualTo(false);
    }

    @Test
    @DisplayName("update 는 성공하면 true 를 반환한다.")
    void update_success_return_true() {
        //given
        UpdateReviewRequest reviewRequest = getUpdateRequest();
        doReturn(1).when(reviewRepository).update(any());

        //when
        boolean updateResult = reviewService.update(reviewRequest);

        //then
        verify(reviewRepository, times(1)).update(any());
        Assertions.assertThat(updateResult).isEqualTo(true);
    }

    @Test
    @DisplayName("update 는 실패하면 false 를 반환한다.")
    void update_fail_return_false() {
        //given
        UpdateReviewRequest reviewRequest = getUpdateRequest();
        doReturn(0).when(reviewRepository).update(any());

        //when
        boolean updateResult = reviewService.update(reviewRequest);

        //then
        verify(reviewRepository, times(1)).update(any());
        Assertions.assertThat(updateResult).isEqualTo(false);
    }


    @Test
    @DisplayName("delete 는 성공하면 true 를 반환한다.")
    void delete_success_return_true() {
        String hospitalId = "existHospital";
        String memberId = "existMember";
        DeleteReviewRequest reviewRequest = DeleteReviewRequest.builder()
                .reviewUniqueId(2L)
                .hospitalUniqueId(hospitalId)
                .writer(memberId)
                .build();

        Review existReview = Review.builder()
                .reviewUniqueId(1L)
                .hospitalUniqueId(hospitalId)
                .star(5)
                .writer(memberId)
                .content("좋았어요.")
                .visitDate(new Date(2023, 11, 23))
                .build();

        //when
        doReturn(1).when(reviewRepository).delete(any());

        boolean updateResult = reviewService.delete(reviewRequest);

        //then
        verify(reviewRepository, times(1)).delete(any());
        Assertions.assertThat(updateResult).isEqualTo(true);
    }


    @Test
    @DisplayName("delete 는 실패하면 false 를 반환한다.")
    void delete_fail_return_false() {
        String hospitalId = "existHospital";
        String memberId = "existMember";
        DeleteReviewRequest reviewRequest = DeleteReviewRequest.builder()
                .reviewUniqueId(2L)
                .hospitalUniqueId(hospitalId)
                .writer(memberId)
                .build();

        Review existReview = Review.builder()
                .reviewUniqueId(1L)
                .hospitalUniqueId(hospitalId)
                .star(5)
                .writer(memberId)
                .content("좋았어요.")
                .visitDate(new Date(2023, 11, 23))
                .build();

        //when
        doReturn(0).when(reviewRepository).delete(any());

        boolean updateResult = reviewService.delete(reviewRequest);

        //then
        verify(reviewRepository, times(1)).delete(any());
        Assertions.assertThat(updateResult).isEqualTo(false);
    }

}
