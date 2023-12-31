package flab.docdoc.hospital.controller;


import flab.docdoc.hospital.request.AddHospitalRequest;
import flab.docdoc.hospital.request.UpdateHospitalRequest;
import flab.docdoc.hospital.response.HospitalResponse;
import flab.docdoc.hospital.service.HospitalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hospital")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    @PostMapping
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid AddHospitalRequest request) {
        hospitalService.save(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid UpdateHospitalRequest request) {
        hospitalService.update(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{hospitalUniqueId}")
    public ResponseEntity<HospitalResponse> getHospital(@PathVariable String hospitalUniqueId) {
        HospitalResponse response = hospitalService.getHospitalResponse(hospitalUniqueId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
