package flab.docdoc.hospital.service;

import flab.docdoc.hospital.domain.Hospital;
import flab.docdoc.hospital.domain.HospitalSubject;
import flab.docdoc.hospital.request.AddHospitalRequest;

import java.util.List;


public interface HospitalService {

    public void isExistHospital(Long hospitalId);
    public Hospital findById(Long hospitalId);
    public Hospital findByMemberUniqueId(Long memberUniqueId);

    public void save(AddHospitalRequest request);

    public void saveSubject(Long hospitalId, List<HospitalSubject> subjects);

    public void update(AddHospitalRequest request);
    public void updateHospitalAdmin(Long hospitalUniqueId, Long memberUniqueId);
    public void deleteHospitalAdmin(Long hospitalUniqueId, Long memberUniqueId);


}
