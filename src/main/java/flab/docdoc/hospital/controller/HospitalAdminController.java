package flab.docdoc.hospital.controller;


import flab.docdoc.hospital.request.UpdateHospitalAdminRequest;
import flab.docdoc.hospital.service.HospitalAdminService;
import flab.docdoc.hospital.service.HospitalAdminServiceImpl;
import flab.docdoc.hospital.service.HospitalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hospital/admin")
@RequiredArgsConstructor
public class HospitalAdminController {

    private final HospitalAdminService hospitalAdminService;

    @PutMapping("/add")
    public ResponseEntity<HttpStatus> updateAdmin(@RequestBody @Valid UpdateHospitalAdminRequest request) {
        hospitalAdminService.addHospitalAdmin(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/delete")
    public ResponseEntity<HttpStatus> deleteAdmin(@RequestBody @Valid UpdateHospitalAdminRequest request) {
        hospitalAdminService.deleteHospitalAdmin(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
