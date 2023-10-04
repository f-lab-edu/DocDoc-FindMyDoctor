package flab.docdoc.hospitalSubInfo.repository;

import flab.docdoc.hospitalSubInfo.domain.Holiday;
import flab.docdoc.hospitalSubInfo.domain.Workday;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface HolidayRepository {

    public int countByHospitalUniqueId(String hospitalUniqueId);

    public int saveHolidays(List<Holiday> holidays);

    public int deleteByHospitalUniqueId(String hospitalUniqueId);
}
