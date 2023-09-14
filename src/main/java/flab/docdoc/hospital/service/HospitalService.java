package flab.docdoc.hospital.service;

import flab.docdoc.hospital.request.AddHospitalRequest;


public interface HospitalService {


//    public void isExistHospital(Long hospitalId);
//    public Hospital findById(Long hospitalId);
    //public Hospital findByMemberUniqueId(Long memberUniqueId);

    public void save(AddHospitalRequest request);

}
