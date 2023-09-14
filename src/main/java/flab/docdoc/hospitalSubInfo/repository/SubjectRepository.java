package flab.docdoc.hospitalSubInfo.repository;

import flab.docdoc.hospitalSubInfo.domain.Contact;
import flab.docdoc.hospitalSubInfo.domain.HospitalSubject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SubjectRepository {

    public Long countByHospitalUniqueId(Long hospitalUniqueId);

    public int saveSubjects(List<HospitalSubject> subjects);

    public int deleteByHospitalUniqueId(Long hospitalUniqueId);
}
