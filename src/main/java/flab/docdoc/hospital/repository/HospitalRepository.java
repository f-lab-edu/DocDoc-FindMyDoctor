package flab.docdoc.hospital.repository;

import flab.docdoc.hospital.domain.Hospital;
import flab.docdoc.hospital.response.HospitalDetailResponse;
import flab.docdoc.review.domain.HospitalStatistics;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface HospitalRepository {


    public Hospital findByUniqueId(final String hospitalUniqueId);


    public int save(Hospital hospital);

    public int update(Hospital hospital);
    public int updateAdmin(@Param("uniqueId") final String hospitalUniqueId, @Param("memberUniqueId") final Long memberUniqueId);
    public int updateStatistics(HospitalStatistics statistics);

    HospitalDetailResponse getHospital(final String hospitalUniqueId);
}
