package flab.docdoc.member.service;

import flab.docdoc.member.domain.Member;
import flab.docdoc.member.request.LoginRequest;
import flab.docdoc.member.response.MemberResponse;

public interface LoginService {

    public MemberResponse login(LoginRequest request);

    public void logout();

}
