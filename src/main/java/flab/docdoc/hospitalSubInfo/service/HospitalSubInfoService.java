package flab.docdoc.hospitalSubInfo.service;

import flab.docdoc.hospitalSubInfo.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class HospitalSubInfoService {

    private final ContactService contactService;
    private final SubjectService subjectService;
    private final WorkdayService workdayService;
    private final HolidayService holidayService;
    private final TagService tagService;


    public void save(Long hospitalUniqueId, List<Contact> contacts, Set<Subject> subjects) {

        contactService.saveContacts(hospitalUniqueId, contacts);
        subjectService.saveSubjects(hospitalUniqueId, subjects);
    }

    public void update(Long hospitalUniqueId, List<Contact> contacts, Set<Subject> subjects, List<Workday> workdays, Set<Holiday> holidays, Set<Tag> tags) {
        contactService.saveContacts(hospitalUniqueId, contacts);
        subjectService.saveSubjects(hospitalUniqueId, subjects);
        workdayService.saveWorkdays(hospitalUniqueId,workdays);
        holidayService.saveHolidays(hospitalUniqueId, holidays);
        tagService.saveTags(hospitalUniqueId, tags);
    }




}
