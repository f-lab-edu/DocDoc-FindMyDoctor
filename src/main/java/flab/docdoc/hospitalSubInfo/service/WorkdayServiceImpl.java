package flab.docdoc.hospitalSubInfo.service;

import flab.docdoc.hospitalSubInfo.domain.Contact;
import flab.docdoc.hospitalSubInfo.domain.Workday;
import flab.docdoc.hospitalSubInfo.repository.ContactRepository;
import flab.docdoc.hospitalSubInfo.repository.WorkdayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkdayServiceImpl implements WorkdayService {

    private final WorkdayRepository workdayRepository;

    @Override
    public void saveWorkdays(Long hospitalUniqueId, List<Workday> workdays) {
        List<Workday> newWorkdays = Workday.of(hospitalUniqueId, workdays);

        Long count = workdayRepository.countByHospitalUniqueId(hospitalUniqueId);
        if (count > 0) {
            int deleteCount = workdayRepository.deleteByHospitalUniqueId(hospitalUniqueId);
            if (count != deleteCount) throw new IllegalArgumentException("삭제 오류");
        }

        int insertCount = workdayRepository.saveWorkdays(newWorkdays);
        if (insertCount < 0 || insertCount != newWorkdays.size()) throw new IllegalArgumentException("입력 오류");
    }



}
