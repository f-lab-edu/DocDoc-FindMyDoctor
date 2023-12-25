package flab.docdoc.member.service;


import flab.docdoc.member.domain.Member;
import flab.docdoc.member.request.AddMemberRequest;
import flab.docdoc.member.request.UpdateMemberRequest;

import java.util.Optional;

public interface MemberService {

    public Optional<Member> findByLoginId(final String loginId);

    public void save(AddMemberRequest request);
    public void update(UpdateMemberRequest request, final String loginId);
    public void updateRole(final String loginId, final Member.Role role);




}
