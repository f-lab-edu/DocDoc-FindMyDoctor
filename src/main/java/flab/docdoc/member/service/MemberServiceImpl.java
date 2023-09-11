package flab.docdoc.member.service;


import flab.docdoc.common.contact.Contact;
import flab.docdoc.hospital.service.HospitalService;
import flab.docdoc.member.domain.Member;
import flab.docdoc.member.repository.MemberRepository;
import flab.docdoc.member.request.AddMemberRequest;
import flab.docdoc.member.request.UpdateMemberRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private boolean isExistMember(String longinId) {
        Member member = findByLoginId(longinId);
        if(member == null) {
            return false;
        }
        return true;
    }

    @Override
    public Member findByUniqueId(Long memberUniqueId) {
        if(memberUniqueId  == null) {
            throw new IllegalArgumentException("아이디를 입력해 주세요.");
        }
        return memberRepository.findByUniqueId(memberUniqueId);
    }

    private boolean isExistMember(Long memberUniqueId) {
        Member member = findByUniqueId(memberUniqueId);
        if(member == null) {
            return false;
        }
        return true;
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



}
