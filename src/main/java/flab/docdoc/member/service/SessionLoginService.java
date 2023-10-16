package flab.docdoc.member.service;

import flab.docdoc.member.domain.Member;
import flab.docdoc.member.request.LoginRequest;
import flab.docdoc.member.response.MemberResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SessionLoginService implements LoginService{

    private static final String LOGIN_MEMBER_ID = "LOGIN_MEMBER_ID";

    private final HttpSession httpSession;
    private final MemberService memberService;

    @Override
    public MemberResponse login(LoginRequest request) {
        checkValidAuthRequest(request);

        Member existMember = memberService.findByLoginId(request.getLoginId());

        if (!existMember.getLoginId().equals(request.getLoginId()) || !existMember.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("아이디 또는 비밀번호를 다시 확인해주세요.");
        }

        httpSession.setAttribute(LOGIN_MEMBER_ID, request.getLoginId());

        return MemberResponse.of(existMember);
    }

    @Override
    public void logout() {
        httpSession.invalidate();
    }

    @Override
    public String getLoginId() {
        return (String) httpSession.getAttribute(LOGIN_MEMBER_ID);
    }

    @Override
    public void isLogin() {
        if (getLoginId() == null ) {
            throw new IllegalArgumentException("로그인 상태가 아닙니다. 다시 확인해주세요.");
        }

    }

    private void checkValidAuthRequest(LoginRequest request) {
        if (request.getLoginId() == null || request.getPassword() == null) {
            throw new IllegalArgumentException("로그인 정보가 존재하지 않습니다. 다시 확인해주세요.");
        }
    }

}
