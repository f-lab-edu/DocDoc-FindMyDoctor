package flab.docdoc.hospitalSubInfo.service;

import flab.docdoc.hospitalSubInfo.domain.HospitalTag;
import flab.docdoc.hospitalSubInfo.domain.Tag;
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
        Long count = tagRepository.count(hospitalUniqueId);
        if (count > 0) {
            int deleteCount = tagRepository.delete(hospitalUniqueId);
            if (count != deleteCount) {
                throw new IllegalArgumentException("태그 삭제 오류. 다시 확인해주세요.");
            }
        }

        if (tags.size() == 0) {
            return;
        }

        List<HospitalTag> newTags = HospitalTag.of(hospitalUniqueId, tags);

        int insertCount = tagRepository.save(newTags);
        if (insertCount < 0 || insertCount != newTags.size()) {
            throw new IllegalArgumentException("태그 입력 오류. 다시 확인해주세요.");
        }
    }


}
