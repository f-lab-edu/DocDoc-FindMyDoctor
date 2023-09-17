package flab.docdoc.hospitalSubInfo.service;

import flab.docdoc.hospitalSubInfo.domain.Contact;
import flab.docdoc.hospitalSubInfo.domain.HospitalSubject;
import flab.docdoc.hospitalSubInfo.domain.Subject;
import flab.docdoc.hospitalSubInfo.repository.ContactRepository;
import flab.docdoc.hospitalSubInfo.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Override
    public void saveSubjects(Long hospitalUniqueId, Set<Subject> subjects) {
        List<HospitalSubject> newSubjects = HospitalSubject.of(hospitalUniqueId, subjects);

        Long count = subjectRepository.countByHospitalUniqueId(hospitalUniqueId);
        if (count > 0) {
            int deleteCount = subjectRepository.deleteByHospitalUniqueId(hospitalUniqueId);
            if (count != deleteCount) throw new IllegalArgumentException("삭제 오류");
        }

        int insertCount = subjectRepository.saveSubjects(newSubjects);
        if (insertCount < 0 || insertCount != subjects.size()) throw new IllegalArgumentException("입력 오류");
    }


}
