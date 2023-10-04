package flab.docdoc.hospitalSubInfo.repository;

import flab.docdoc.hospitalSubInfo.domain.HospitalSubject;
import flab.docdoc.hospitalSubInfo.domain.HospitalTag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TagRepository {

    public int countByHospitalUniqueId(String hospitalUniqueId);

    public int saveTags(List<HospitalTag> tags);

    public int deleteByHospitalUniqueId(String hospitalUniqueId);
}
