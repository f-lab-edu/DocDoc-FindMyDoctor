package flab.docdoc.review.service;


import flab.docdoc.hospital.service.HospitalService;
import flab.docdoc.member.service.MemberService;
import flab.docdoc.review.domain.HospitalStatistics;
import flab.docdoc.review.domain.Review;
import flab.docdoc.review.repository.ReviewRepository;
import flab.docdoc.review.request.ReviewRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final HospitalService hospitalService;
    private final MemberService memberService;
    private final ReviewRepository reviewRepository;

    @Transactional
    public void save(ReviewRequest request) {
        hospitalService.getHospital(request.getHospitalUniqueId());
        memberService.getMember(request.getMemberUniqueId());

        //TODO : A 회원이 B 병원에 리뷰를 등록한 적이 있으면 등록되도록 하면 안된다.
        // getHistory(memberUniqueId, hospitalUniqueId)가 존재하면 등록 X

        Review newReview = ReviewRequest.toNewReview(request);

        int saveResult = reviewRepository.save(newReview);
        if (saveResult != 1) {
            throw new IllegalArgumentException("리뷰 등록 실패 ! 다시 확인해주세요.");
        }

        HospitalStatistics statistics = HospitalStatistics.toSave(newReview);

        hospitalService.updateStatistics(statistics);

        //TODO : Review History 등록

    }

    @Transactional
    public void update(ReviewRequest request) {
        Review existReview = getReview(request.getReviewUniqueId());
        Review newReview = ReviewRequest.toUpdateBuilder(request, existReview);
        //TODO : A 회원이 B 병원에 리뷰를 등록한 적이 없으면, 삭제한 이력이 있으면 업데이트 수정되면 하면 안된다.
        // getHistory(memberUniqueId, hospitalUniqueId)가 null 이거나 status.equals(DELETE) 라면 업데이트 X

        //TODO : session 멤버가 수정하려는 멤버와 같은지 확인해야한다.
        int updateResult = reviewRepository.update(newReview);
        if (updateResult != 1) {
            throw new IllegalArgumentException("리뷰 업데이트 실패! 다시 확인해주세요.");
        }

        HospitalStatistics statistics = HospitalStatistics.toUpdate(existReview, newReview);

        hospitalService.updateStatistics(statistics);
        //TODO : Review History 등록
    }

    @Transactional
    public void delete(final Long reviewUniqueId) {

        Review existReview = getReview(reviewUniqueId);

        //TODO : A 회원이 B 병원에 리뷰를 등록한 적이 없으면, 삭제한 이력이 있으면 삭제하면 하면 안된다. Review History로 관리할 예정

        //TODO : session 멤버가 수정하려는 멤버와 같은지 확인해야한다.

        int deleteResult = reviewRepository.delete(existReview.getReviewUniqueId());
        if (deleteResult != 1) {
            throw new IllegalArgumentException("리뷰 삭제 실패! 다시 확인해주세요.");
        }

        HospitalStatistics statistics = HospitalStatistics.toDelete(existReview);

        hospitalService.updateStatistics(statistics);
        //TODO : Review History 등록
    }


    private Review findByUniqueId(Long reviewUniqueId) {
        if (reviewUniqueId == null) {
            throw new IllegalArgumentException("입력값을 다시 확인해주세요.");
        }
        return reviewRepository.findByUniqueId(reviewUniqueId);
    }

    public Review getReview(Long reviewUniqueId) {
        Review existReview = findByUniqueId(reviewUniqueId);
        if(existReview == null) {
            throw new IllegalArgumentException("존재하지 않는 리뷰 입니다.");
        }
        return existReview;
    }


}
