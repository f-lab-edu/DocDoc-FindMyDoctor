package flab.docdoc.common.util;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SessionUtil {

    private static final String LOGIN_MEMBER_ID = "memberId";

    public static String getLoginMemberId() {
        return LOGIN_MEMBER_ID;
    }

    public static Optional<Object> getCurrentMember() {
        return Optional.ofNullable(getSession().getAttribute(getLoginMemberId()));
    }

    private static HttpSession getSession() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
    }

    public static void setAttribute(String memberKey, String memberValue) {
        getSession().setAttribute(memberKey, memberValue);
    }

    public static void  inValidate() {
        getSession().invalidate();
    }
}
