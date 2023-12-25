package flab.docdoc.common.util;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.Optional;

public class SessionUtil {

    public static final String LOGIN_MEMBER_ID = "memberId";

    public static Optional<Object> getCurrentMember() {
        return Optional.ofNullable(getSession().getAttribute(LOGIN_MEMBER_ID));
    }

    private static HttpSession getSession() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
    }

    public static void setAttribute(String memberValue) {
        getSession().setAttribute(LOGIN_MEMBER_ID, memberValue);
    }

    public static void  inValidate() {
        getSession().invalidate();
    }
}
