package flab.docdoc.fake;

import flab.docdoc.hospitalSubInfo.domain.*;
import flab.docdoc.hospitalSubInfo.service.HospitalSubInfoService;

import java.util.List;
import java.util.Set;

public class FakeHospitalSubInfoService implements HospitalSubInfoService {
    @Override
    public void save(String hospitalUniqueId, Set<Subject> subjects) {

    }

    @Override
    public void update(String hospitalUniqueId, Set<Subject> subjects, List<Workday> workdays, Set<Holiday> holidays, Set<Tag> tags) {

    }

    @Override
    public SubInfo findByHospitalUniqueId(String hospitalUniqueId) {
        return null;
    }
}
