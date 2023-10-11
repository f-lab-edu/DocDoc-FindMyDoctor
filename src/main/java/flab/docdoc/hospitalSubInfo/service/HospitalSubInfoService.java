package flab.docdoc.hospitalSubInfo.service;

import flab.docdoc.hospitalSubInfo.domain.Subject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class HospitalSubInfoService {

    private final SubjectService subjectService;



    public void save(String hospitalUniqueId, Set<Subject> subjects) {
        subjectService.saveSubjects(hospitalUniqueId, subjects);
    }

    public void update(String hospitalUniqueId, Set<Subject> subjects) {
        subjectService.saveSubjects(hospitalUniqueId, subjects);
//        workdayService.saveWorkdays(hospitalUniqueId,workdays);
//        holidayService.saveHolidays(hospitalUniqueId, holidays);
//        tagService.saveTags(hospitalUniqueId, tags);
    }




}
