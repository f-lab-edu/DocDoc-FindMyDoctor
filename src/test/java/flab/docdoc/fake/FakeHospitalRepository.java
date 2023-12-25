package flab.docdoc.fake;

import flab.docdoc.hospital.domain.Hospital;
import flab.docdoc.hospital.repository.HospitalRepository;
import flab.docdoc.hospital.response.HospitalResponse;
import flab.docdoc.review.domain.HospitalStatistics;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FakeHospitalRepository implements HospitalRepository {
    private final Map<String, Hospital> data = Collections.synchronizedMap(new HashMap<>());


    @Override
    public Hospital findByUniqueId(String hospitalId) {
        return data.get(hospitalId);
    }

    @Override
    public HospitalResponse findHospitalResponseByUniqueId(String hospitalId) {
        return null;
    }

    @Override
    public int save(Hospital hospital) {
        data.putIfAbsent(hospital.getUniqueId(), hospital);
        return 1;
    }

    @Override
    public int update(Hospital hospital) {
        Hospital existHospital = findByUniqueId(hospital.getUniqueId());
        data.remove(existHospital);
        data.put(existHospital.getUniqueId(), hospital);
        return 1;
    }

    @Override
    public int updateAdmin(String hospitalUniqueId, String loginId) {
        Hospital existHospital = findByUniqueId(hospitalUniqueId);
        Hospital newHospital = Hospital.builder()
                .uniqueId(existHospital.getUniqueId())
                .businessName(existHospital.getBusinessName())
                .sidoNm(existHospital.getSidoNm())
                .sgguNm(existHospital.getSgguNm())
                .addr(existHospital.getAddr())
                .tel(existHospital.getTel())
                .xPos(existHospital.getXPos())
                .yPos(existHospital.getYPos())
                .adminId(loginId)
                .build();
        data.remove(existHospital);
        data.put(existHospital.getUniqueId(), newHospital);
        return 1;
    }

    @Override
    public int updateStatistics(HospitalStatistics statistics) {
        Hospital existHospital = findByUniqueId(statistics.getHospitalUniqueId());
        Hospital newHospital = Hospital.builder()
                .uniqueId(existHospital.getUniqueId())
                .businessName(existHospital.getBusinessName())
                .sidoNm(existHospital.getSidoNm())
                .sgguNm(existHospital.getSgguNm())
                .addr(existHospital.getAddr())
                .tel(existHospital.getTel())
                .xPos(existHospital.getXPos())
                .yPos(existHospital.getYPos())
                .star(existHospital.getStar() + statistics.getStar())
                .reviewCount(existHospital.getReviewCount() + statistics.getReviewCount())
                .build();

        data.remove(existHospital);
        data.put(existHospital.getUniqueId(), newHospital);
        return 1;
    }
}
