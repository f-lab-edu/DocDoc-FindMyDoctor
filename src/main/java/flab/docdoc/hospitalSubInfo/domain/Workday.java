package flab.docdoc.hospitalSubInfo.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

import java.time.LocalTime;
import java.util.List;

@Getter
public class Workday {



    private int workFlag;
    private String hospitalUniqueId;
    private Weekday weekday;
    private LocalTime stTime;
    private LocalTime edTime;

    @Builder(builderClassName = "WorkdayBuilder", builderMethodName = "WorkdayBuilder")
    public Workday(int workFlag, String hospitalUniqueId, Weekday weekday, LocalTime stTime, LocalTime edTime) {
        Assert.notNull(workFlag, "workFlag must be not null");
        Assert.notNull(weekday, "weekday must be not null");

        switch(workFlag) {
            case 0:
                Assert.isNull(stTime, weekday + " is not workday. please check " + weekday + "'s work flag(value: " + workFlag + "). stTime must be null");
                Assert.isNull(edTime, weekday + " is not workday. please check " + weekday + "'s work flag(value: " + workFlag + "). edTime must be null");
                break;
            case 1:
                Assert.notNull(stTime, "stTime must be not null");
                Assert.notNull(edTime, "edTime must be not null");
                break;
            default:
                throw new IllegalArgumentException("workFlag must be 0 or 1");

        }
        this.workFlag = workFlag;
        this.hospitalUniqueId = hospitalUniqueId;
        this.weekday = weekday;
        this.stTime = stTime;
        this.edTime = edTime;
    }

    public static List<Workday> of(String hospitalUniqueId, List<Workday> workdays) {
        Assert.notNull(hospitalUniqueId, "hospitalUniqueId must be not null");
        return workdays.stream()
                .map(v-> Workday.WorkdayBuilder()
                        .workFlag(v.getWorkFlag())
                        .hospitalUniqueId(hospitalUniqueId)
                        .weekday(v.getWeekday())
                        .stTime(v.getStTime())
                        .edTime(v.getEdTime())
                        .build())
                .toList();
    }
}
