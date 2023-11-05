package flab.docdoc.hospitalSubInfo.repository;

import flab.docdoc.hospitalSubInfo.domain.HospitalTag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TagRepository {

    public Long count(String hospitalUniqueId);

    public int save(List<HospitalTag> tags);

    public int delete(String hospitalUniqueId);
}
