package flab.docdoc.hospitalSubInfo.service;

import flab.docdoc.hospitalSubInfo.domain.Subject;

import java.util.Set;

public interface SubjectService {

    public void saveSubjects(String hospitalUniqueId, Set<Subject> subjects);

}
