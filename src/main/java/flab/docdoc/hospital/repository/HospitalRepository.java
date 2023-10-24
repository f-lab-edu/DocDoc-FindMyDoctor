package flab.docdoc.hospital.repository;

import flab.docdoc.hospital.domain.Hospital;
import flab.docdoc.hospital.response.HospitalResponse;
import flab.docdoc.review.domain.HospitalStatistics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface HospitalRepository {

    public Hospital findByUniqueId(String hospitalId);
    public HospitalResponse findHospitalResponseByUniqueId(String hospitalId);

    public int save(Hospital hospital);

    public int update(Hospital hospital);
    public int updateAdmin(@Param("uniqueId") final String hospitalUniqueId, @Param("memberUniqueId") final Long memberUniqueId);
    public int updateStatistics(HospitalStatistics statistics);

}
