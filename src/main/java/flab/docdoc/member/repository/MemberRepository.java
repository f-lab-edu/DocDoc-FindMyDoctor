package flab.docdoc.member.repository;

import flab.docdoc.member.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MemberRepository {

    public Member findByLoginId(final String loginId);

    public Member findByUniqueId(final Long uniqueId);

    public int save(Member member);

    public int update(Member member);
    public int updateMemberRole(@Param("uniqueId") final Long memberUniqueId, @Param("role") final Member.Role role);
}
