package flab.docdoc.common.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import static flab.docdoc.common.util.SessionUtil.getCurrentMember;


@Aspect
@Component
@RequiredArgsConstructor
public class AuthAspect {

    @Before("@annotation(flab.docdoc.common.aop.CheckLogin)")
    public void execute(JoinPoint joinPoint) {
        getCurrentMember().orElseThrow(() -> {throw new IllegalArgumentException("로그인 상태가 아닙니다.");});
    }


}
