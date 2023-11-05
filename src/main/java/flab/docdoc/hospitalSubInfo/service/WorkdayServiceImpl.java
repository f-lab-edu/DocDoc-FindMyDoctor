package flab.docdoc.hospitalSubInfo.service;

import flab.docdoc.hospitalSubInfo.domain.Workday;
import flab.docdoc.hospitalSubInfo.repository.WorkdayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkdayServiceImpl implements WorkdayService {

    private final WorkdayRepository workdayRepository;

    @Override
    public void saveWorkdays(String hospitalUniqueId, List<Workday> workdays) {
        Long count = workdayRepository.count(hospitalUniqueId);
        if (count > 0) {
            int deleteCount = workdayRepository.delete(hospitalUniqueId);
            if (count != deleteCount) {
                throw new IllegalArgumentException("진료일 삭제 오류. 다시 확인해주세요.");
            }
        }

        if (workdays.size() == 0) {
            return;
        }
        List<Workday> newWorkdays = Workday.of(hospitalUniqueId, workdays);


        int insertCount = workdayRepository.save(newWorkdays);
        if (insertCount < 0 || insertCount != newWorkdays.size()) {
            throw new IllegalArgumentException("진료일 입력 오류. 다시 확인해주세요.");
        }
    }



}
