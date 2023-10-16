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
        //login check
        loginService.isLogin();

        memberService.updateMemberInfo(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/myInfo")
    public ResponseEntity<MemberResponse> getMyInfo() {
        //login check
        loginService.isLogin();

        String loginId = loginService.getLoginId();
        return new ResponseEntity<>(memberService.findMemberInfo(loginId), HttpStatus.OK);
    }

    @GetMapping("/{loginId}")
    public ResponseEntity<MemberResponse> getMyInfo(@PathVariable String loginId) {
        return new ResponseEntity<>(memberService.findMemberInfo(loginId), HttpStatus.OK);
    }


}
