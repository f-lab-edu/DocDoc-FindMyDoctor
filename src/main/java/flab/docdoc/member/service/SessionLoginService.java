package flab.docdoc.member.service;

import flab.docdoc.member.domain.Member;
import flab.docdoc.member.request.LoginRequest;
import flab.docdoc.member.response.MemberResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static flab.docdoc.common.util.SessionUtil.*;


@Service
@RequiredArgsConstructor
public class SessionLoginService implements LoginService{

    private final MemberService memberService;

    @Override
    public MemberResponse login(LoginRequest request) {
        checkValidAuthRequest(request);

        Member existMember = memberService.findByLoginId(request.getLoginId())
                .orElseThrow(() -> {throw new IllegalArgumentException("존재하지 않는 회원 입니다.");});

        if (!existMember.getLoginId().equals(request.getLoginId()) || !existMember.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("아이디 또는 비밀번호를 다시 확인해주세요.");
        }

        setAttribute(getLoginMemberId(), request.getLoginId());
        return MemberResponse.of(existMember);
    }

    @Override
    public void logout() {
        inValidate();
    }


    private void checkValidAuthRequest(LoginRequest request) {
        if (request.getLoginId() == null || request.getPassword() == null) {
            throw new IllegalArgumentException("로그인 정보가 존재하지 않습니다. 다시 확인해주세요.");
        }
    }

}
