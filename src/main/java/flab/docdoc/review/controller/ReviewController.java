package flab.docdoc.review.controller;

import flab.docdoc.review.request.ReviewRequest;
import flab.docdoc.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static flab.docdoc.common.util.SessionUtil.getCurrentMember;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<HttpStatus> save(@RequestBody  ReviewRequest request) {

        String loginId = (String) getCurrentMember().orElseThrow(() -> {throw new IllegalArgumentException("로그인 상태가 아닙니다.");});

        reviewService.save(request, loginId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> update(@RequestBody ReviewRequest request) {
        String loginId = (String) getCurrentMember().orElseThrow(() -> {throw new IllegalArgumentException("로그인 상태가 아닙니다.");});

        reviewService.update(request, loginId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{reviewUniqueId}")
    public ResponseEntity<HttpStatus> delete(@PathVariable final Long reviewUniqueId) {
        String loginId = (String) getCurrentMember().orElseThrow(() -> {throw new IllegalArgumentException("로그인 상태가 아닙니다.");});

        reviewService.delete(reviewUniqueId, loginId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
