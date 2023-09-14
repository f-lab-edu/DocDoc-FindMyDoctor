package flab.docdoc.common.vo.location.service;

import flab.docdoc.common.vo.location.domain.Location;

public interface LocationService {

    public Location findWithParentLocation(String LocationName, String parentLocationName);
}
