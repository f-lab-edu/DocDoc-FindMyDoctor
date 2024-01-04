package flab.docdoc.hospital.controller;

import flab.docdoc.hospital.response.HospitalDetailResponse;
import flab.docdoc.hospital.response.HospitalResponse;
import flab.docdoc.hospital.service.HospitalSearchService;
import flab.docdoc.hospitalSubInfo.domain.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static flab.docdoc.hospitalSubInfo.domain.Subject.getLableList;

@RestController
@RequestMapping("/api/hospital/search")
@RequiredArgsConstructor
@Slf4j
public class HospitalSearchController {

    private final HospitalSearchService hospitalSearchService;

    @GetMapping("/list/{subject}")
    public ResponseEntity<List<HospitalResponse>> getHospitalList(@PathVariable String subject,
                                                                  @RequestParam(required = false) String sort,
                                                                  @RequestParam(required = false) LocalDate visitDate,
                                                                  @RequestParam(required = false) List<String> tags)
                                                                  {

        return new ResponseEntity<>(hospitalSearchService.getHospitalList(subject, sort, visitDate, tags), HttpStatus.OK);
    }
}
