package flab.docdoc.hospitalSubInfo.service;

import flab.docdoc.hospitalSubInfo.domain.Contact;
import flab.docdoc.hospitalSubInfo.domain.HospitalSubject;
import flab.docdoc.hospitalSubInfo.domain.Subject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class HospitalSubInfoService {

    private final ContactService contactService;
    private final SubjectService subjectService;

    public void save(Long hospitalUniqueId, List<Contact> contacts, Set<Subject> subjects) {

        contactService.saveContacts(hospitalUniqueId, contacts);
        subjectService.saveSubjects(hospitalUniqueId, subjects);
    }




}
