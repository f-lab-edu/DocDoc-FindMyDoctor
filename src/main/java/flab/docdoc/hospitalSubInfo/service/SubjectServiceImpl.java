package flab.docdoc.hospitalSubInfo.service;

import flab.docdoc.hospitalSubInfo.domain.HospitalSubject;
import flab.docdoc.hospitalSubInfo.domain.Subject;
import flab.docdoc.hospitalSubInfo.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Override
    @Transactional
    public void saveSubjects(String hospitalUniqueId, Set<Subject> subjects) {
        List<HospitalSubject> newSubjects = HospitalSubject.of(hospitalUniqueId, subjects);

        int count = subjectRepository.countByHospitalUniqueId(hospitalUniqueId);
        if (count > 0) {
            int deleteCount = subjectRepository.deleteByHospitalUniqueId(hospitalUniqueId);
            if (count != deleteCount) throw new IllegalArgumentException("진료과목 수정 오류. 다시 확인해주세요.");
        }

        int insertCount = subjectRepository.saveSubjects(newSubjects);
        if (insertCount < 0 || insertCount != subjects.size()) throw new IllegalArgumentException("진료과목 등록 요류. 다시 확인해주세요.");
    }

    @Override
    public List<Subject> findByHospitalUniqueId(String hospitalUniqueId) {
        if (hospitalUniqueId == null) {
            throw new IllegalArgumentException("병원 아이디가 입력되지 않았습니다.");
        }

        return subjectRepository.findByHospitalUniqueId(hospitalUniqueId);
    }


}
