package flab.docdoc.hospitalSubInfo.repository;

import flab.docdoc.hospitalSubInfo.domain.Workday;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface WorkdayRepository {

    public Long count(String hospitalUniqueId);

    public int save(List<Workday> workdays);

    public int delete(String hospitalUniqueId);
}
