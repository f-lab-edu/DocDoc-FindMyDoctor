package flab.docdoc.member.service;


import flab.docdoc.member.domain.Member;
import flab.docdoc.member.request.AddMemberRequest;
import flab.docdoc.member.request.UpdateMemberRequest;
import org.springframework.stereotype.Service;


public interface MemberService {

    public Member findByLoginId(String loginId);
    public void save(AddMemberRequest request);


    public void updateMemberInfo(UpdateMemberRequest request);
    public Member findByUniqueId(Long memberUniqueId);


}
