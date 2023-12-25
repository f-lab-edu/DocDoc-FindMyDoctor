package flab.docdoc.common.aop.CheckLogin;

import flab.docdoc.common.exception.CustomErrorCode;
import flab.docdoc.common.exception.CustomException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import static flab.docdoc.common.util.SessionUtil.getCurrentMember;


@Aspect
@Component
public class CheckLoginAspect {

    @Around("@annotation(flab.docdoc.common.aop.CheckLogin.CheckLogin)")
    public Object execute(final ProceedingJoinPoint joinPoint) throws Throwable{
        getCurrentMember().orElseThrow(() -> {
            throw new CustomException(CustomErrorCode.NOT_LOGIN);
        });
        return joinPoint.proceed();
    }

}
