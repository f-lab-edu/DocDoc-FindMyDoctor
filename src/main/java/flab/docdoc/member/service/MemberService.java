package flab.docdoc.member.service;


import flab.docdoc.member.domain.Member;
import org.springframework.stereotype.Service;


public interface MemberService {


    public void isExistMember(String loginId);

    public void save(Member member);

    public void update(Member member);
    public void updateHospitalAdmin(Long memberUniqueId);


}
