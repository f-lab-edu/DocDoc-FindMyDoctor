package flab.docdoc.hospitalSubInfo.service;

import flab.docdoc.hospitalSubInfo.domain.*;

import java.util.List;
import java.util.Set;

public interface HospitalSubInfoService {

    public void save(String hospitalUniqueId, Set<Subject> subjects);

    public void update(String hospitalUniqueId, Set<Subject> subjects, List<Workday> workdays, Set<Holiday> holidays, Set<Tag> tags);

    public SubInfo findByHospitalUniqueId(String hospitalUniqueId);

}
