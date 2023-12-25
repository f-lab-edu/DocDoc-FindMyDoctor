package flab.docdoc.common;

import  com.fasterxml.jackson.databind.ObjectMapper;
import flab.docdoc.common.aop.CheckWriter.CheckWriterAspect;
import flab.docdoc.common.exception.CustomErrorCode;
import flab.docdoc.common.exception.CustomException;
import flab.docdoc.review.controller.ReviewController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.HashMap;
import java.util.Map;
import static flab.docdoc.common.util.SessionUtil.LOGIN_MEMBER_ID;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

public class CheckWriterAspectTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    private ReviewController getProxyController() {
        AspectJProxyFactory factory = new AspectJProxyFactory(Mockito.mock(ReviewController.class));
        factory.addAspect(new CheckWriterAspect());
        return factory.getProxy();
    }

    @Test
    @DisplayName("writer 와 로그인 멤버가 같으면 성공한다.")
    void success() throws Exception {
        final String memberId = "A_Member";
        ReviewController proxyController = getProxyController();
        MockHttpSession session = new MockHttpSession();

        session.setAttribute(LOGIN_MEMBER_ID, memberId);

        Map<String, String> input = new HashMap<>();
        input.put("reviewUniqueId", "1");
        input.put("hospitalUniqueId", "A_Hospital");
        input.put("writer", "A_Member");
        input.put("star", "FIVE");
        input.put("content", "괜찮았어요");

        this.mockMvc = MockMvcBuilders.standaloneSetup(proxyController).build();

        //when
        //then
        mockMvc.perform(put("/api/review")
                .session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)));
    }

    @Test
    @DisplayName("writer 와 로그인 멤버가 다르면 실패한다.")
    void fail_not_match_writer() throws Exception {
        //given
        final String memberId = "A_Member";
        ReviewController proxyController = getProxyController();
        MockHttpSession session = new MockHttpSession();

        session.setAttribute(LOGIN_MEMBER_ID, memberId);

        Map<String, String> input = new HashMap<>();
        input.put("reviewUniqueId", "1");
        input.put("hospitalUniqueId", "A_Hospital");
        input.put("writer", "B_Member");
        input.put("star", "FIVE");
        input.put("content", "괜찮았어요");

        this.mockMvc = MockMvcBuilders.standaloneSetup(proxyController).build();

        //when
        //then
        assertThatThrownBy(() -> mockMvc.perform(put("/api/review")
                                        .session(session)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(input)))).hasCause(new CustomException(CustomErrorCode.NOT_MATCHED_WRITER));
    }


    @Test
    @DisplayName("세션에 로그인 정보가 없으면 실패한다.")
    void fail_not_exist_member() throws Exception {
        //given
        final String memberId = "A_Member";
        ReviewController proxyController = getProxyController();
        MockHttpSession session = new MockHttpSession();

        session.setAttribute(LOGIN_MEMBER_ID, null);

        Map<String, String> input = new HashMap<>();
        input.put("reviewUniqueId", "1");
        input.put("hospitalUniqueId", "A_Hospital");
        input.put("writer", "B_Member");
        input.put("star", "FIVE");
        input.put("content", "괜찮았어요");

        this.mockMvc = MockMvcBuilders.standaloneSetup(proxyController).build();

        //when
        //then
        assertThatThrownBy(() -> mockMvc.perform(put("/api/review")
                                        .session(session)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(input)))).hasCause(new CustomException(CustomErrorCode.NOT_EXIST_MEMBER));
    }
}
