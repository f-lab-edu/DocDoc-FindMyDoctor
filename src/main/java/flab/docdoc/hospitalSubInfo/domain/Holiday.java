package flab.docdoc.hospitalSubInfo.domain;


import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Getter
public class Holiday {

    private Long hospitalUniqueId;
    private LocalDate holiday;

    @Builder(builderClassName = "HolidayBuilder", builderMethodName = "HolidayBuilder")
    public Holiday(Long hospitalUniqueId, LocalDate holiday) {
        Assert.notNull(holiday, "holiday must be not null");

        this.hospitalUniqueId = hospitalUniqueId;
        this.holiday = holiday;
    }

    public static List<Holiday> of(Long hospitalUniqueId, Set<Holiday> holidays) {
        Assert.notNull(hospitalUniqueId, "hospitalUniqueId must be not null");
        return  holidays.stream()
                .map((v) -> Holiday.HolidayBuilder()
                        .hospitalUniqueId(hospitalUniqueId)
                        .holiday(v.getHoliday())
                        .build())
                .toList();
    }
}
