package flab.docdoc.hospitalSubInfo.repository;

import flab.docdoc.hospitalSubInfo.domain.HospitalSubject;
import flab.docdoc.hospitalSubInfo.domain.Subject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SubjectRepository {

    public int countByHospitalUniqueId(final String hospitalUniqueId);

    public int saveSubjects(List<HospitalSubject> subjects);

    public int deleteByHospitalUniqueId(final String hospitalUniqueId);

    public List<Subject> findByHospitalUniqueId(final String hospitalUniqueId);
}
