package flab.docdoc.hospitalSubInfo.service;

import flab.docdoc.hospitalSubInfo.domain.Contact;
import flab.docdoc.hospitalSubInfo.domain.HospitalSubject;
import flab.docdoc.hospitalSubInfo.domain.Subject;

import java.util.List;
import java.util.Set;

public interface SubjectService {

    public void saveSubjects(Long hospitalUniqueId, Set<Subject> subjects);

}
