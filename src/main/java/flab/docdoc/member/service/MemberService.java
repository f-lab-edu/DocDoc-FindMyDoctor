package flab.docdoc.member.service;


import flab.docdoc.member.domain.Member;
import flab.docdoc.member.request.AddMemberRequest;
import flab.docdoc.member.request.UpdateMemberRequest;
import flab.docdoc.member.response.MemberResponse;

import java.util.Optional;

public interface MemberService {

    public Optional<Member> findByLoginId(String loginId);
    public Optional<Member> findByUniqueId(Long memberUniqueId);

    public void save(AddMemberRequest request);
    public void updateMemberInfo(UpdateMemberRequest request);
    public void updateMemberRole(final Long memberUniqueId, final Member.Role role);




}
