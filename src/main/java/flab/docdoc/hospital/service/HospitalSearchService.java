package flab.docdoc.hospital.service;

import flab.docdoc.hospital.repository.HospitalSearchRepository;
import flab.docdoc.hospital.response.HospitalDetailResponse;
import flab.docdoc.hospital.response.HospitalResponse;
import flab.docdoc.hospitalSubInfo.domain.HospitalTag;
import flab.docdoc.hospitalSubInfo.domain.Tag;
import flab.docdoc.hospitalSubInfo.domain.Weekday;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static flab.docdoc.hospitalSubInfo.domain.Weekday.ToEnum;


@Service
@RequiredArgsConstructor
public class HospitalSearchService {

    private final HospitalSearchRepository hospitalSearchRepository;

    public List<HospitalResponse> getHospitalList(final String subject, final String sort, final LocalDate visitDate, final List<String> tags) {
        String paramDayOfWeek = null;
        String paramVisitDate = null;
        if (visitDate != null) {
            paramDayOfWeek = getDayOfWeek(visitDate);
            paramVisitDate = visitDate.toString();
        }

        return hospitalSearchRepository.getHospitalList(subject, sort, paramVisitDate, paramDayOfWeek, tags);
    }

    private String getDayOfWeek(final LocalDate day) {
        int intDayOfWeek = day.getDayOfWeek().getValue();
        return ToEnum(intDayOfWeek).toString();
    }

}
