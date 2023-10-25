package flab.docdoc.member.service;


import flab.docdoc.member.domain.Member;
import flab.docdoc.member.request.AddMemberRequest;
import flab.docdoc.member.request.UpdateMemberRequest;
import flab.docdoc.member.response.MemberResponse;

public interface MemberService {

    public Member findByLoginId(String loginId);
    public Member findByUniqueId(Long memberUniqueId);

    public MemberResponse findMemberInfo(String loginId);

    public Member getMember(Long memberUniqueId);


    public boolean isExistMember(String loginId);
    public boolean isExistMember(Long memberUniqueId);

    public void save(AddMemberRequest request);
    public void updateMemberInfo(UpdateMemberRequest request);
    public void updateMemberRole(final Long memberUniqueId, final Member.Role role);




}
