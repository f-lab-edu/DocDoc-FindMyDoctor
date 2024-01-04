package flab.docdoc.review.controller;

import flab.docdoc.common.aop.CheckLogin.CheckLogin;
import flab.docdoc.common.aop.CheckWriter.CheckWriter;
import flab.docdoc.review.request.AddReviewRequest;
import flab.docdoc.review.request.DeleteReviewRequest;
import flab.docdoc.review.request.UpdateReviewRequest;
import flab.docdoc.review.service.ReviewFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewFacade reviewFacade;

    //TODO @CheckLogin
    //TODO @CheckWriter
    @PostMapping
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid AddReviewRequest request) {
        reviewFacade.save(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CheckLogin
    @CheckWriter
    @PutMapping
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid UpdateReviewRequest request) {
        reviewFacade.update(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //TODO @CheckLogin
    //TODO @CheckWriter
    @DeleteMapping
    public ResponseEntity<HttpStatus> delete(@RequestBody @Valid DeleteReviewRequest request) {
        reviewFacade.delete(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
