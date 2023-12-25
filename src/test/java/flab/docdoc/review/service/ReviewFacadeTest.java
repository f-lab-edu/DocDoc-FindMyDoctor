package flab.docdoc.review.service;

import flab.docdoc.common.exception.CustomErrorCode;
import flab.docdoc.common.exception.CustomException;
import flab.docdoc.hospital.domain.Hospital;
import flab.docdoc.hospital.service.HospitalService;
import flab.docdoc.member.domain.Member;
import flab.docdoc.member.service.MemberService;
import flab.docdoc.review.domain.Review;
import flab.docdoc.review.request.UpdateReviewRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static flab.docdoc.review.service.ReviewServiceTestV2.getReview;
import static flab.docdoc.review.service.ReviewServiceTestV2.getUpdateRequest;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReviewFacadeTest {

    @InjectMocks
    private ReviewFacade reviewFacade;

    @Mock
    private HospitalService hospitalService;

    @Mock
    private MemberService memberService;

    @Mock
    private ReviewServiceV2 reviewService;

    public static Hospital getHospital() {
        return Hospital.builder()
                .uniqueId("A_Hospital")
                .businessName("이미 동록된 병원")
                .tel("010-010-010")
                .sidoNm("서울특별시")
                .sgguNm("강남구")
                .addr("도봉구 무슨대로 12번길")
                .adminId("aaa")
                .star(0L)
                .reviewCount(0L)
                .build();
    }

    public static Member getMember() {
        return Member.builder()
                .loginId("A_Member")
                .email("asdf1234@naver.com")
                .password("1q2w3e4r")
                .build();
    }

    @Test
    @DisplayName("미등록된 리뷰 id 가 주어지면 update 는 실패한다.")
    void update_fail_not_exist_review() {
        //given
        UpdateReviewRequest request = getUpdateRequest();
        doReturn(Optional.ofNullable(null)).when(reviewService).findByUniqueId(any());

        //when
        CustomException e = assertThrows(CustomException.class, () -> reviewFacade.update(request));

        //then
        Assertions.assertThat(e.getErrorCode()).isEqualTo(CustomErrorCode.NOT_EXIST_REVIEW);
    }
    @Test
    @DisplayName("미등록된 병원 id 가 주어지면 update 는 실패한다.")
    void update_fail_not_exist_hospital() {
        //given
        UpdateReviewRequest reviewRequest = getUpdateRequest();
        Review existReview = getReview();
        doReturn(Optional.ofNullable(existReview)).when(reviewService).findByUniqueId(any());
        doReturn(Optional.ofNullable(null)).when(hospitalService).findByUniqueId(any());

        //when
        CustomException e = assertThrows(CustomException.class, () -> reviewFacade.update(reviewRequest));

        //then
        Assertions.assertThat(e.getErrorCode()).isEqualTo(CustomErrorCode.NOT_EXIST_HOSPITAL);
    }

    @Test
    @DisplayName("미등록된 회원 id 가 주어지면 update 는 실패한다.")
    void update_fail_not_exist_member() {
        //given
        UpdateReviewRequest reviewRequest = getUpdateRequest();
        Review existReview = getReview();
        Hospital existHospital = getHospital();

        doReturn(Optional.ofNullable(existReview)).when(reviewService).findByUniqueId(any());
        doReturn(Optional.ofNullable(existHospital)).when(hospitalService).findByUniqueId(any());
        doReturn(Optional.ofNullable(null)).when(memberService).findByLoginId(any());


        //when
        CustomException e = assertThrows(CustomException.class, () -> reviewFacade.update(reviewRequest));

        //then
        Assertions.assertThat(e.getErrorCode()).isEqualTo(CustomErrorCode.NOT_EXIST_MEMBER);
    }

    @Test
    @DisplayName("update 는 리뷰 업데이트가 false 를 반환하면 실패한다.")
    void update_fail_fail_command() {
        //given
        UpdateReviewRequest reviewRequest = getUpdateRequest();
        Review existReview = getReview();
        Hospital existHospital = getHospital();
        Member existMember = getMember();

        doReturn(Optional.ofNullable(existReview)).when(reviewService).findByUniqueId(any());
        doReturn(Optional.ofNullable(existHospital)).when(hospitalService).findByUniqueId(any());
        doReturn(Optional.ofNullable(existMember)).when(memberService).findByLoginId(any());
        doReturn(false).when(reviewService).update(any());

        //when
        CustomException e = assertThrows(CustomException.class, () -> reviewFacade.update(reviewRequest));

        //then
        Assertions.assertThat(e.getErrorCode()).isEqualTo(CustomErrorCode.FAIL_COMMAND);
    }

    @Test
    @DisplayName("update 성공")
    void update_success() {
        //given
        UpdateReviewRequest reviewRequest = getUpdateRequest();
        Review existReview = getReview();
        Hospital existHospital = getHospital();
        Member existMember = getMember();

        doReturn(Optional.ofNullable(existReview)).when(reviewService).findByUniqueId(any());
        doReturn(Optional.ofNullable(existHospital)).when(hospitalService).findByUniqueId(any());
        doReturn(Optional.ofNullable(existMember)).when(memberService).findByLoginId(any());
        doReturn(true).when(reviewService).update(any());

        //when
        reviewFacade.update(reviewRequest);

        //then
        verify(hospitalService, times(1)).updateStatistics(any());
    }
}
