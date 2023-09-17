package flab.docdoc.hospital.service;

import flab.docdoc.hospital.domain.Hospital;
import flab.docdoc.hospital.request.AddHospitalRequest;
import flab.docdoc.hospital.request.UpdateHospitalRequest;


public interface HospitalService {

    public Hospital findByUniqueId(Long hospitalUniqueId);

    public void save(AddHospitalRequest request);

    public void update(UpdateHospitalRequest request);

}
