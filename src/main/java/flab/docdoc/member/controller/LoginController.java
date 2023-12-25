package flab.docdoc.member.controller;

import flab.docdoc.member.request.LoginRequest;
import flab.docdoc.member.response.MemberResponse;
import flab.docdoc.member.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<MemberResponse> login(@RequestBody @Valid LoginRequest request) {
        MemberResponse memberResponse = loginService.login(request);

        return new ResponseEntity<>(memberResponse, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public void logout() {
        //loginService.isLogin();
        loginService.logout();
    }

}
