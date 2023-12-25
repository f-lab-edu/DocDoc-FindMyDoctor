package flab.docdoc.common.aop.CheckWriter;

import flab.docdoc.common.exception.CustomErrorCode;
import flab.docdoc.common.exception.CustomException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import static flab.docdoc.common.util.SessionUtil.getCurrentMember;


@Aspect
@Component
public class CheckWriterAspect {

    @Around("@annotation(flab.docdoc.common.aop.CheckWriter.CheckWriter) && args(writerMatch, ..)")
    public Object execute(final ProceedingJoinPoint joinPoint, WriterMatch writerMatch) throws Throwable{
        writerMatch.checkWriter(
                (String) getCurrentMember().orElseThrow(() -> {
                    throw new CustomException(CustomErrorCode.NOT_EXIST_MEMBER);
                })
        );
        return joinPoint.proceed();
    }

}
