package flab.docdoc.member.service;


import flab.docdoc.member.domain.Member;
import flab.docdoc.member.repository.MemberRepository;
import flab.docdoc.member.request.AddMemberRequest;
import flab.docdoc.member.request.UpdateMemberRequest;
import flab.docdoc.member.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        Assert.notNull(loginId, "loginId must not be null");
        return Optional.ofNullable(memberRepository.findByLoginId(loginId));
    }

    @Override
    public Optional<Member> findByUniqueId(Long memberUniqueId) {
        Assert.notNull(memberUniqueId, "memberUniqueId must not be null");
        return Optional.ofNullable(memberRepository.findByUniqueId(memberUniqueId));
    }

    @Override
    public void save(AddMemberRequest request) {
        findByLoginId(request.getLoginId()).ifPresent(a -> {throw new IllegalArgumentException("이미 존재하는 회원입니다.");});

        Member newMember = AddMemberRequest.of(request);
        if (memberRepository.save(newMember) != 1) {
            throw new IllegalArgumentException("회원가입 오류! 다시 시도해주세요.");
        }
    }

    @Override
    public void updateMemberInfo(UpdateMemberRequest request) {
        findByUniqueId(request.getUniqueId()).orElseThrow(() -> {throw new IllegalArgumentException("존재하지 않는 회원입니다.");});

        Member updateMember = UpdateMemberRequest.of(request);
        int updateResult =  memberRepository.update(updateMember);
        if (updateResult != 1) {
            throw new IllegalArgumentException("입력 정보를 다시 확인해주세요.");
        }
    }

    @Override
    public void updateMemberRole(final Long memberUniqueId, final Member.Role role) {
        if (memberUniqueId == null || role == null) {
            throw new IllegalArgumentException("입력 정보 오류. 회원 정보 또는 권한을 다시 확인해주세요.");
        }

        int updateResult = memberRepository.updateMemberRole(memberUniqueId, role);
        if(updateResult != 1) {
            throw new IllegalArgumentException("회원 정보 수정실패. 다시 시도해주세요.");
        }
    }


}
