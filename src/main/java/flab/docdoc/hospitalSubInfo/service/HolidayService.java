package flab.docdoc.hospitalSubInfo.service;

import flab.docdoc.hospitalSubInfo.domain.Holiday;
import flab.docdoc.hospitalSubInfo.domain.Workday;

import java.util.List;
import java.util.Set;

public interface HolidayService {

    public void saveHolidays(Long hospitalUniqueId, Set<Holiday> holidays);


}
