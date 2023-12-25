//package flab.docdoc.review.service;
//
//
//import flab.docdoc.common.exception.CustomErrorCode;
//import flab.docdoc.common.exception.CustomException;
//import flab.docdoc.hospital.domain.Hospital;
//import flab.docdoc.hospital.service.HospitalService;
//import flab.docdoc.member.domain.Member;
//import flab.docdoc.member.service.MemberService;
//import flab.docdoc.review.domain.HospitalStatistics;
//import flab.docdoc.review.domain.Review;
//import flab.docdoc.review.repository.ReviewRepository;
//import flab.docdoc.review.request.AddReviewRequest;
//import flab.docdoc.review.request.UpdateReviewRequest;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.Assert;
//
//import java.util.Optional;
//
//@Slf4j
//@RequiredArgsConstructor
//public class ReviewServiceImpl implements ReviewService{
//
//    private final HospitalService hospitalService;
//    private final MemberService memberService;
//    private final ReviewRepository reviewRepository;
//
//    @Transactional
//    public void save(AddReviewRequest request, final String loginId) {
//        Hospital existHospital = hospitalService.findByUniqueId(request.getHospitalUniqueId())
//                .orElseThrow(() -> {throw new IllegalArgumentException("존재하지 않는 병원 입니다. 다시 확인해주세요");});
//        Member existMember = memberService.findByLoginId(loginId)
//                .orElseThrow(() -> {throw new IllegalArgumentException("존재하지 않는 회원 입니다. 다시 확인해주세요");});
//
//        //TODO : A 회원이 B 병원에 리뷰를 등록한 적이 있으면 등록되도록 하면 안된다.
//        // getHistory(memberUniqueId, hospitalUniqueId)가 존재하면 등록 X
//
//        Review newReview = request.toDomain();
//
//        int saveResult = reviewRepository.save(newReview);
//        if (saveResult != 1) {
//            throw new IllegalArgumentException("리뷰 등록 실패 ! 다시 확인해주세요.");
//        }
//
//        HospitalStatistics statistics = HospitalStatistics.builder()
//                                                        .hospitalUniqueId(newReview.getHospitalUniqueId())
//                                                        .star(newReview.getStar())
//                                                        .reviewCount(1L)
//                                                        .build();
//
//        hospitalService.updateStatistics(statistics);
//
//        //TODO : Review History 등록
//
//    }
//
//    @Transactional
//    public void update(UpdateReviewRequest request, final String loginId) {
//        Review existReview = findByUniqueId(request.getReviewUniqueId())
//                .orElseThrow(() -> {throw new CustomException(CustomErrorCode.NOT_EXIST_REVIEW);});
//
//        Member existMember = memberService.findByLoginId(request.getWriter())
//                .orElseThrow(() -> {throw new CustomException(CustomErrorCode.NOT_EXIST_MEMBER);});
//
//        if (!StringUtils.equals(request.getWriter(), existReview.getWriter())) {
//            throw new CustomException(CustomErrorCode.NOT_MATCHED_WRITER);
//        }
//
//        Review newReview = request.toDomain();
//
//        //TODO : A 회원이 B 병원에 리뷰를 등록한 적이 없으면, 삭제한 이력이 있으면 업데이트 수정되면 하면 안된다.
//        // getHistory(memberUniqueId, hospitalUniqueId)가 null 이거나 status.equals(DELETE) 라면 업데이트 X
//
//        int updateResult = reviewRepository.update(newReview);
//        if (updateResult != 1) {
//            throw new IllegalArgumentException("리뷰 업데이트 실패! 다시 확인해주세요.");
//        }
//
//        HospitalStatistics statistics = HospitalStatistics.builder()
//                                                        .hospitalUniqueId(request.getHospitalUniqueId())
//                                                        .star(newReview.getStar() - existReview.getStar())
//                                                        .reviewCount(0L)
//                                                        .build();
//
//        hospitalService.updateStatistics(statistics);
//        //TODO : Review History 등록
//    }
//
//    @Transactional
//    public void delete(final Long reviewUniqueId, final String loginId) {
//
//        Review existReview = findByUniqueId(reviewUniqueId)
//                .orElseThrow(() -> {throw new IllegalArgumentException("존재하지 않는 리뷰 입니다. 다시 확인해주세요");});
//
//        Member existMember = memberService.findByLoginId(loginId)
//                .orElseThrow(() -> {throw new IllegalArgumentException("존재하지 않는 회원 입니다. 다시 확인해주세요");});
//
//        if (!existReview.getWriter().equals(existMember.getLoginId())) {
//            throw new IllegalArgumentException("로그인 한 회원이 쓴 리뷰가 아닙니다.");
//        }
//        //TODO : A 회원이 B 병원에 리뷰를 등록한 적이 없으면, 삭제한 이력이 있으면 삭제하면 하면 안된다. Review History로 관리할 예정
//
//
//        int deleteResult = reviewRepository.delete(existReview.getReviewUniqueId());
//        if (deleteResult != 1) {
//            throw new IllegalArgumentException("리뷰 삭제 실패! 다시 확인해주세요.");
//        }
//
//        HospitalStatistics statistics = HospitalStatistics.builder()
//                                                        .hospitalUniqueId(existReview.getHospitalUniqueId())
//                                                        .star(-existReview.getStar())
//                                                        .reviewCount(-1L)
//                                                        .build();
//
//
//        hospitalService.updateStatistics(statistics);
//        //TODO : Review History 등록
//    }
//
//
//    public Optional<Review> findByUniqueId(Long reviewUniqueId) {
//        Assert.notNull(reviewUniqueId, "reviewUniqueId must not be null");
//        return Optional.ofNullable(reviewRepository.findByUniqueId(reviewUniqueId));
//    }
//
//
//
//}
