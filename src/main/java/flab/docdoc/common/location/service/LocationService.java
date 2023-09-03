package flab.docdoc.common.location.service;

import flab.docdoc.common.location.domain.Location;
import org.springframework.stereotype.Service;

public interface LocationService {

    public Location withParentLocation(String LocationName, String parentLocationName);
}
