package flab.docdoc.hospitalSubInfo.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

import java.time.LocalTime;
import java.util.List;

@Getter
public class Workday {


    private String hospitalUniqueId;
    private Weekday weekday;
    private LocalTime stTime;
    private LocalTime edTime;

    @Builder(builderClassName = "WorkdayBuilder", builderMethodName = "WorkdayBuilder")
    public Workday(String hospitalUniqueId, Weekday weekday, LocalTime stTime, LocalTime edTime) {
        Assert.notNull(weekday, "weekday must be not null");
        Assert.notNull(stTime, "stTime must be not null");
        Assert.notNull(edTime, "edTime must be not null");

        this.hospitalUniqueId = hospitalUniqueId;
        this.weekday = weekday;
        this.stTime = stTime;
        this.edTime = edTime;
    }

    public static List<Workday> of(String hospitalUniqueId, List<Workday> workdays) {
        Assert.notNull(hospitalUniqueId, "hospitalUniqueId must be not null");
//        if (workdays.size() == 0 || workdays == null) throw new IllegalArgumentException("workdays size must not be 0");
        return workdays.stream()
                .map(v-> Workday.WorkdayBuilder()
                        .hospitalUniqueId(hospitalUniqueId)
                        .weekday(v.getWeekday())
                        .stTime(v.getStTime())
                        .edTime(v.getEdTime())
                        .build())
                .toList();
    }
}
