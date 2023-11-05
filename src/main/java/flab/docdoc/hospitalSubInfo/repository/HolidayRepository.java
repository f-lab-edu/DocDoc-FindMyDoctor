package flab.docdoc.hospitalSubInfo.repository;

import flab.docdoc.hospitalSubInfo.domain.Holiday;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface HolidayRepository {

    public Long count(String hospitalUniqueId);

    public int save(List<Holiday> holidays);

    public int delete(String hospitalUniqueId);
}
