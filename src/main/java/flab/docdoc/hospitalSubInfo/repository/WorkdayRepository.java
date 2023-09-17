package flab.docdoc.hospitalSubInfo.repository;

import flab.docdoc.hospitalSubInfo.domain.HospitalSubject;
import flab.docdoc.hospitalSubInfo.domain.Workday;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface WorkdayRepository {

    public Long countByHospitalUniqueId(Long hospitalUniqueId);

    public int saveWorkdays(List<Workday> workdays);

    public int deleteByHospitalUniqueId(Long hospitalUniqueId);
}
