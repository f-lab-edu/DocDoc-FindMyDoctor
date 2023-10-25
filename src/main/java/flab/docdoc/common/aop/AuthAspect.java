package flab.docdoc.common.aop;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static flab.docdoc.member.service.SessionLoginService.LOGIN_MEMBER_ID;

@Aspect
@Component
@RequiredArgsConstructor
public class AuthAspect {

    private final HttpSession httpSession;

    @Before("@annotation(flab.docdoc.common.aop.CheckLogin)")
    public void execute(JoinPoint joinPoint) {
        boolean isLogin = isLogin();
        if (!isLogin) {
            throw new IllegalArgumentException("로그인 되어있지 않습니다.");
        }
    }

    private boolean isLogin() {
        return httpSession.getAttribute(LOGIN_MEMBER_ID) != null;
    }


}
