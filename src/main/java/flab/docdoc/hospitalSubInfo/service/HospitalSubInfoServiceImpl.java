package flab.docdoc.hospitalSubInfo.service;

import flab.docdoc.hospitalSubInfo.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class HospitalSubInfoServiceImpl implements HospitalSubInfoService{

    private final SubjectService subjectService;
    private final WorkdayService workdayService;
    private final HolidayService holidayService;
    private final TagService tagService;



    public void save(String hospitalUniqueId, Set<Subject> subjects) {
        subjectService.saveSubjects(hospitalUniqueId, subjects);
    }

    public void update(String hospitalUniqueId, Set<Subject> subjects, List<Workday> workdays, Set<Holiday> holidays, Set<Tag> tags) {
        subjectService.saveSubjects(hospitalUniqueId, subjects);
        workdayService.saveWorkdays(hospitalUniqueId,workdays);
        holidayService.saveHolidays(hospitalUniqueId, holidays);
        tagService.saveTags(hospitalUniqueId, tags);
    }

    public SubInfo findByHospitalUniqueId(String hospitalUniqueId) {
        List<Subject> subjects = subjectService.findByHospitalUniqueId(hospitalUniqueId);
        return SubInfo.of(subjects);
    }




}
