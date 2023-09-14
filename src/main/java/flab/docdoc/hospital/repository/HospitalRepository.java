package flab.docdoc.hospital.repository;

import flab.docdoc.hospital.domain.Hospital;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface HospitalRepository {


    public Hospital findByUniqueId(Long hospitalId);

//    public Hospital findByUniqueIdAndAdminUniqueId(Long uniqueId, Long adminUniqueId);
    //select * from hospital where unique_id = #{uniqueId0} and admin_unique_id = #{adminUniqueId};

    public int save(Hospital hospital);


}
