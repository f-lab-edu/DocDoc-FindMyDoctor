package flab.docdoc.member.service;


import flab.docdoc.member.domain.Member;
import flab.docdoc.member.repository.MemberRepository;
import flab.docdoc.member.request.AddMemberRequest;
import flab.docdoc.member.request.UpdateMemberRequest;
import flab.docdoc.member.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public Member findByLoginId(String loginId) {
        if(loginId  == null) {
            throw new IllegalArgumentException("아이디를 입력해 주세요.");
        }
        return memberRepository.findByLoginId(loginId);
    }

    @Override
    public Member findByUniqueId(Long memberUniqueId) {
        if(memberUniqueId  == null) {
            throw new IllegalArgumentException("아이디를 입력해 주세요.");
        }
        return memberRepository.findByUniqueId(memberUniqueId);
    }

    @Override

    public MemberResponse findMemberInfo(String loginId) {
        Member existMember = findByLoginId(loginId);
        if (existMember == null) {
            throw new IllegalArgumentException("존재하지 않는 회원 입니다.");
        }
        return MemberResponse.of(existMember);
    }

    @Override
    public Member getMember(Long memberUniqueId) {
        Member existMember = findByUniqueId(memberUniqueId);
        if (existMember == null) {
            throw new IllegalArgumentException("존재하지 않는 회원 입니다. 다시 확인해주세요.");
        }
        return existMember;
    }

    @Override
    public boolean isExistMember(String longinId) {
        return findByLoginId(longinId) != null;
    }

    @Override
    public boolean isExistMember(Long memberUniqueId) {
        return findByUniqueId(memberUniqueId) != null;
    }

    @Override
    public void save(AddMemberRequest request) {
        if(isExistMember(request.getLoginId())) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }
        Member newMember = AddMemberRequest.of(request);
        if (memberRepository.save(newMember) != 1) {
            throw new IllegalArgumentException("회원가입 오류! 다시 시도해주세요.");
        }
    }

    @Override
    public void updateMemberInfo(UpdateMemberRequest request) {
        if(!isExistMember(request.getUniqueId())) {
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }
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
