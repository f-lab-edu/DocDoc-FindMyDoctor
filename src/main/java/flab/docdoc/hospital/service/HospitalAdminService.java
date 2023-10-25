package flab.docdoc.hospital.service;

import flab.docdoc.hospital.request.UpdateHospitalAdminRequest;

public interface HospitalAdminService {

    public void addHospitalAdmin(UpdateHospitalAdminRequest request);

    public void deleteHospitalAdmin(UpdateHospitalAdminRequest request);
}
