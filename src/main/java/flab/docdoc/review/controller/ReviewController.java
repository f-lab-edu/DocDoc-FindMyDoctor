package flab.docdoc.review.controller;

import flab.docdoc.review.request.ReviewRequest;
import flab.docdoc.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<HttpStatus> save(@RequestBody  ReviewRequest request) {
        reviewService.save(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> update(@RequestBody ReviewRequest request) {
        reviewService.update(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{reviewUniqueId}")
    public ResponseEntity<HttpStatus> delete(@PathVariable final Long reviewUniqueId) {
        reviewService.delete(reviewUniqueId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
