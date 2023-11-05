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
        Long count = holidayRepositroy.count(hospitalUniqueId);
        if (count > 0) {
            int deleteCount = holidayRepositroy.delete(hospitalUniqueId);
            if (count != deleteCount) throw new IllegalArgumentException("쉬는날 삭제 오류. 다시 확인해주세요.");
        }

        if (holidays.size() == 0) {
            return;
        }
        List<Holiday> newHolidays = Holiday.of(hospitalUniqueId, holidays);



        int insertCount = holidayRepositroy.save(newHolidays);
        if (insertCount < 0 || insertCount != newHolidays.size()) throw new IllegalArgumentException("쉬는날 입력 오류. 다시 확인해주세요.");
    }



}
