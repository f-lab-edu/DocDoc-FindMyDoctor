package flab.docdoc.hospitalSubInfo.service;

import flab.docdoc.hospitalSubInfo.domain.Workday;

import java.util.List;

public interface WorkdayService {

    public void saveWorkdays(String hospitalUniqueId, List<Workday> workdays);

}
