package flab.docdoc.hospital.service;

import flab.docdoc.hospital.domain.Hospital;
import flab.docdoc.hospital.request.AddHospitalRequest;
import flab.docdoc.hospital.request.UpdateHospitalRequest;
import flab.docdoc.hospital.response.HospitalResponse;
import flab.docdoc.review.domain.HospitalStatistics;

import java.util.Optional;


public interface HospitalService {

    public Optional<Hospital> findByUniqueId(String hospitalUniqueId);

    public void save(AddHospitalRequest request);

    public void update(UpdateHospitalRequest request, final String loginId);
    public void updateAdmin(final String hospitalUniqueId, final String loginId);
    public void updateStatistics(HospitalStatistics statistics);

    public HospitalResponse getHospitalResponse(String hospitalUniqueId);

}
