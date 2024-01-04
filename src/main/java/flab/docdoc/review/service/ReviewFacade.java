package flab.docdoc.review.service;

import flab.docdoc.common.exception.CustomErrorCode;
import flab.docdoc.common.exception.CustomException;
import flab.docdoc.hospital.domain.Hospital;
import flab.docdoc.hospital.service.HospitalService;
import flab.docdoc.member.domain.Member;
import flab.docdoc.member.service.MemberService;
import flab.docdoc.review.domain.HospitalStatistics;
import flab.docdoc.review.domain.Review;
import flab.docdoc.review.request.AddReviewRequest;
import flab.docdoc.review.request.DeleteReviewRequest;
import flab.docdoc.review.request.UpdateReviewRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewFacade {

    private final ReviewServiceV2 reviewService;
    private final MemberService memberService;
    private final HospitalService hospitalService;

    @Transactional
    public void save(AddReviewRequest request) {
        Hospital existHospital = hospitalService.findByUniqueId(request.getHospitalUniqueId())
                .orElseThrow(() -> {throw new CustomException(CustomErrorCode.NOT_EXIST_HOSPITAL);});

        Member existMember = memberService.findByLoginId(request.getWriter())
                .orElseThrow(() -> {throw new CustomException(CustomErrorCode.NOT_EXIST_MEMBER);});


        if (!reviewService.save(request)) {
            throw new CustomException(CustomErrorCode.FAIL_COMMAND);
        }

        hospitalService.updateStatistics(HospitalStatistics.builder()
                .hospitalUniqueId(existHospital.getUniqueId())
                .star(request.getStar().getValue())
                .reviewCount(1)
                .build());
    }


    @Transactional
    public void update(UpdateReviewRequest request) {
        Review existReview = reviewService.findByUniqueId(request.getReviewUniqueId())
                .orElseThrow(() -> {throw new CustomException(CustomErrorCode.NOT_EXIST_REVIEW);});

        Hospital existHospital = hospitalService.findByUniqueId(request.getHospitalUniqueId())
                .orElseThrow(() -> {throw new CustomException(CustomErrorCode.NOT_EXIST_HOSPITAL);});

        Member existMember = memberService.findByLoginId(request.getWriter())
                .orElseThrow(() -> {throw new CustomException(CustomErrorCode.NOT_EXIST_MEMBER);});

        if (!reviewService.update(UpdateReviewRequest.builder()
                                                    .reviewUniqueId(existReview.getReviewUniqueId())
                                                    .hospitalUniqueId(existReview.getHospitalUniqueId())
                                                    .writer(existReview.getWriter())
                                                    .star(request.getStar())
                                                    .content(request.getContent())
                                                    .build()
                                                    )) {
            throw new CustomException(CustomErrorCode.FAIL_COMMAND);
        }

        hospitalService.updateStatistics(HospitalStatistics.builder()
                .hospitalUniqueId(existHospital.getUniqueId())
                .star(request.getStar().getValue() - existReview.getStar())
                .reviewCount(0)
                .build());
    }

    @Transactional
    public void delete(DeleteReviewRequest request) {
        Review existReview = reviewService.findByUniqueId(request.getReviewUniqueId())
                .orElseThrow(() -> {throw new CustomException(CustomErrorCode.NOT_EXIST_REVIEW);});

        Hospital existHospital = hospitalService.findByUniqueId(request.getHospitalUniqueId())
                .orElseThrow(() -> {throw new CustomException(CustomErrorCode.NOT_EXIST_HOSPITAL);});

        Member existMember = memberService.findByLoginId(request.getWriter())
                .orElseThrow(() -> {throw new CustomException(CustomErrorCode.NOT_EXIST_MEMBER);});

        if(!reviewService.delete(request)) {
            throw new CustomException(CustomErrorCode.FAIL_COMMAND);
        }

        hospitalService.updateStatistics(HospitalStatistics.builder()
                .hospitalUniqueId(existHospital.getUniqueId())
                .star(-existReview.getStar())
                .reviewCount(-1)
                .build());
    }
}
