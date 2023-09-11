package flab.docdoc.member.repository;

import flab.docdoc.common.contact.Contact;
import flab.docdoc.member.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MemberRepository {

    public Member findByLoginId(String loginId);

    public Member findByUniqueId(Long uniqueId);

    public int save(Member member);

    public int update(Member member);
}
