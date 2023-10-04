package flab.docdoc.hospitalSubInfo.service;

import flab.docdoc.hospitalSubInfo.domain.HospitalSubject;
import flab.docdoc.hospitalSubInfo.domain.HospitalTag;
import flab.docdoc.hospitalSubInfo.domain.Subject;
import flab.docdoc.hospitalSubInfo.domain.Tag;
import flab.docdoc.hospitalSubInfo.repository.SubjectRepository;
import flab.docdoc.hospitalSubInfo.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public void saveTags(String hospitalUniqueId, Set<Tag> tags) {
        List<HospitalTag> newTags = HospitalTag.of(hospitalUniqueId, tags);

        int count = tagRepository.countByHospitalUniqueId(hospitalUniqueId);
        if (count > 0) {
            int deleteCount = tagRepository.deleteByHospitalUniqueId(hospitalUniqueId);
            if (count != deleteCount) throw new IllegalArgumentException("삭제 오류");
        }

        int insertCount = tagRepository.saveTags(newTags);
        if (insertCount < 0 || insertCount != newTags.size()) throw new IllegalArgumentException("입력 오류");
    }


}
