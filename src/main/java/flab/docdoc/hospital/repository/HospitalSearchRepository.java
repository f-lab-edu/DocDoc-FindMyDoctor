package flab.docdoc.hospital.repository;

import flab.docdoc.hospital.response.HospitalDetailResponse;
import flab.docdoc.hospital.response.HospitalResponse;
import flab.docdoc.hospitalSubInfo.domain.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface HospitalSearchRepository {

    public HospitalDetailResponse getHospital(final String hospitalUniqueId);

    public List<HospitalResponse> getHospitalList(@Param("subject") final String subject,
                                                  @Param("sort") final String sort,
                                                  @Param("visitDate") final String visitDate,
                                                  @Param("dayOfWeek") final String dayOfWeek,
                                                  @Param("tags") final List<String> tag);

    }
