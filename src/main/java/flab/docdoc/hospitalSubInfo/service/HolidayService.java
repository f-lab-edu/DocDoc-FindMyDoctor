package flab.docdoc.hospitalSubInfo.service;

import flab.docdoc.hospitalSubInfo.domain.Holiday;

import java.util.Set;

public interface HolidayService {

    public void saveHolidays(String hospitalUniqueId, Set<Holiday> holidays);


}
