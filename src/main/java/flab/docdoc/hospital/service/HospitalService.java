package flab.docdoc.hospital.service;

import flab.docdoc.hospital.domain.Hospital;
import flab.docdoc.hospital.request.AddHospitalRequest;
import flab.docdoc.hospital.request.UpdateHospitalRequest;
import flab.docdoc.hospital.response.HospitalResponse;


public interface HospitalService {

    public Hospital findByUniqueId(String hospitalUniqueId);
    public Hospital getHospital(String hospitalUniqueId);

    public void save(AddHospitalRequest request);

    public void update(UpdateHospitalRequest request);
    public void updateAdmin(final String hospitalUniqueId, final Long memberUniqueId);

    public HospitalResponse getHospitalResponse(String hospitalUniqueId);

}
