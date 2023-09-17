package flab.docdoc.hospitalSubInfo.service;

import flab.docdoc.hospitalSubInfo.domain.Subject;
import flab.docdoc.hospitalSubInfo.domain.Tag;

import java.util.Set;

public interface TagService {

    public void saveTags(Long hospitalUniqueId, Set<Tag> tags);

}
