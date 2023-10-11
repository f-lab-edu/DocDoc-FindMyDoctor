package flab.docdoc.hospital.repository;

import flab.docdoc.hospital.domain.Hospital;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface HospitalRepository {

    public Hospital findByUniqueId(String hospitalId);

    public int save(Hospital hospital);

    public int update(Hospital hospital);

}
