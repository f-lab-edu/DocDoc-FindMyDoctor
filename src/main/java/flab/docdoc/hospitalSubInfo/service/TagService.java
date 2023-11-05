package flab.docdoc.hospitalSubInfo.service;

import flab.docdoc.hospitalSubInfo.domain.Tag;

import java.util.Set;

public interface TagService {

    public void saveTags(String hospitalUniqueId, Set<Tag> tags);

}
