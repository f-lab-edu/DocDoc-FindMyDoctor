package flab.docdoc.member.controller;


import flab.docdoc.member.request.AddMemberRequest;
import flab.docdoc.member.request.UpdateMemberRequest;
import flab.docdoc.member.response.MemberResponse;
import flab.docdoc.member.service.LoginService;
import flab.docdoc.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static flab.docdoc.common.util.SessionUtil.getCurrentMember;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid AddMemberRequest request) {
        memberService.save(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid UpdateMemberRequest request) {

        memberService.updateMemberInfo(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/myInfo")
    public ResponseEntity<MemberResponse> getMyInfo() {

        String loginId = (String) getCurrentMember().orElseThrow(() -> {throw new IllegalArgumentException("로그인 상태가 아닙니다.");});

        MemberResponse memberResponse = MemberResponse.of(memberService.findByLoginId(loginId)
                .orElseThrow(() -> {throw new IllegalArgumentException("존재하지 않는 회원 입니다.");}));
        return new ResponseEntity<>(memberResponse, HttpStatus.OK);
    }

    @GetMapping("/{loginId}")
    public ResponseEntity<MemberResponse> getMyInfo(@PathVariable String loginId) {
        MemberResponse memberResponse = MemberResponse.of(memberService.findByLoginId(loginId)
                .orElseThrow(() -> {throw new IllegalArgumentException("존재하지 않는 회원 입니다.");}));
        return new ResponseEntity<>(memberResponse, HttpStatus.OK);
    }


}
