package flab.docdoc.member.service;


import flab.docdoc.member.domain.Member;
import flab.docdoc.member.request.AddMemberRequest;
import flab.docdoc.member.request.UpdateMemberRequest;

public interface MemberService {

    public Member findByLoginId(String loginId);
    public Member findByUniqueId(Long memberUniqueId);

    public boolean isExistMember(String loginId);
    public boolean isExistMember(Long memberUniqueId);

    public void save(AddMemberRequest request);
    public void updateMemberInfo(UpdateMemberRequest request);




}
