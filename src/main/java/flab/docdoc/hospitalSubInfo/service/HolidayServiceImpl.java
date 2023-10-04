package flab.docdoc.hospitalSubInfo.service;

import flab.docdoc.hospitalSubInfo.domain.Holiday;
import flab.docdoc.hospitalSubInfo.repository.HolidayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class HolidayServiceImpl implements HolidayService {

    private final HolidayRepository holidayRepositroy;

    @Override
    public void saveHolidays(String hospitalUniqueId, Set<Holiday> holidays) {
        List<Holiday> newHolidays = Holiday.of(hospitalUniqueId, holidays);

        int count = holidayRepositroy.countByHospitalUniqueId(hospitalUniqueId);
        if (count > 0) {
            int deleteCount = holidayRepositroy.deleteByHospitalUniqueId(hospitalUniqueId);
            if (count != deleteCount) throw new IllegalArgumentException("삭제 오류");
        }

        int insertCount = holidayRepositroy.saveHolidays(newHolidays);
        if (insertCount < 0 || insertCount != newHolidays.size()) throw new IllegalArgumentException("입력 오류");
    }



}
