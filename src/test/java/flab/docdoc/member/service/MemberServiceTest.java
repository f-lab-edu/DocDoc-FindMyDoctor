package flab.docdoc.member.service;

import flab.docdoc.member.domain.Member;
import flab.docdoc.member.request.AddMemberRequest;
import flab.docdoc.member.request.UpdateMemberRequest;
import flab.docdoc.member.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    void AddMemberRequest_를_이용하여_회원을_생성할_수_있다() {
        //given
        AddMemberRequest request = AddMemberRequest.builder()
                .loginId("test")
                .email("test")
                .password("test")
                .build();

        //when
        memberService.save(request);

        Member member = memberService.findByLoginId(request.getLoginId()).get();

        //then
        Assertions.assertThat(member).isNotNull();
    }

    @Test
    void loginId_가_중복이면_예외를_던진다() {
        //given
        AddMemberRequest request = AddMemberRequest.builder()
                .loginId("test")
                .email("test")
                .password("test")
                .build();

        //when

        //then
        Assertions.assertThatThrownBy(() -> {
            memberService.save(request);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void findByLoginId_는_존재하는_회원을_찾을_수_있다() {
        //given
        String loginId = "midmid";

        //when
        Member member = memberService.findByLoginId(loginId).orElseThrow(() -> {throw new IllegalArgumentException("존재하지 않는 회원 입니다.");});

        //then
        Assertions.assertThat(member.getLoginId()).isEqualTo("midmid");

    }

    @Test
    void findByLoginId_는_존재하지_않는_회원을_찾으면_예외를_던진다() {
      //given
        String loginId = "notnot";

        //when

        //then
        Assertions.assertThatThrownBy(() -> {
            memberService.findByLoginId(loginId).orElseThrow(() -> {throw new IllegalArgumentException("존재하지 않는 회원 입니다.");});
        }).isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void UpdateMemberRequest_를_이용하여_회원을_수정할_수_있다() {
        //given
        String loginId = "test";
        UpdateMemberRequest request = UpdateMemberRequest.builder()
                .email("testUpdate")
                .password("testUpdate")
                .build();

        //when
        memberService.update(request, loginId);
        Member member = memberService.findByLoginId(loginId).get();

        //then
        Assertions.assertThat(member.getPassword()).isEqualTo("testUpdate");
        Assertions.assertThat(member.getEmail()).isEqualTo("testUpdate");
    }

//    @Test
//    void 회원_권한을_변경할_수_있다() {
//        //given
//        String loginId = "test";
//        Member.Role role = Member.Role.ADMIN;
//
//        //when
//        memberService.updateRole(loginId, role);
//        Member member = memberService.findByLoginId(loginId).get();
//
//        //then
//        Assertions.assertThat(member.getRole()).isEqualTo(Member.Role.PUBLIC);
//    }


}