package flab.docdoc.common.vo.location.service;

import flab.docdoc.common.vo.location.domain.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl {

//    private final LocationRepository locationRepository;
//
//    public Location checkCorrectSubLocation(String name, String parentLocationName, String detailLocation) {
//        Location district = findLocation(name);
//        Location city = findLocation(parentLocationName);
//
//        if (!city.getName().equals(district.getParentLocationName())) {
//            throw new IllegalStateException("올바른 지역이 아닙니다.");
//        }
//
//        return Location.builder().name(district.getName()).build();
//    }
//
//
//    private Location findLocation(String name) {
//        return Optional.ofNullable(locationRepository.findByName(name))
//                .orElseThrow(() -> new IllegalStateException("존재하지 않는 지역 입니다."));
//    }
}
