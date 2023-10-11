package flab.docdoc.hospital.service;

import flab.docdoc.hospital.domain.Hospital;
import flab.docdoc.hospital.repository.HospitalRepository;
import flab.docdoc.hospital.request.AddHospitalRequest;
import flab.docdoc.hospital.request.UpdateHospitalRequest;
import flab.docdoc.hospitalSubInfo.service.HospitalSubInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class HospitalServiceImpl implements HospitalService{

    private final HospitalRepository hospitalRepository;
    private final HospitalSubInfoService hospitalSubInfoService;
    //   private final LocationService locationService;

    @Override
    public Hospital findByUniqueId(String hospitalUniqueId) {
        if (hospitalUniqueId == null) throw new IllegalArgumentException("입력 값을 다시 확인해주세요.");
        return hospitalRepository.findByUniqueId(hospitalUniqueId);
    }

    private Boolean isExistHospital(String hospitalUniqueId) {
        Hospital hospital = findByUniqueId(hospitalUniqueId);
        if (hospital == null) return false;
        return true;
    }

    @Override
    @Transactional
    public void save(AddHospitalRequest request) {
        Hospital hospital = AddHospitalRequest.of(request);
        int saveResult = hospitalRepository.save(hospital);

        if (saveResult != 1 || hospital.getUniqueId() == null)
            throw new IllegalArgumentException("병원 등록 실패. 다시 시도해주세요.");

        hospitalSubInfoService.save(hospital.getUniqueId(), request.getSubjects());
    }

    @Override
    @Transactional
    public void update(UpdateHospitalRequest request) {
        Hospital hospital = UpdateHospitalRequest.of(request);
        if (!isExistHospital(hospital.getUniqueId())) throw new IllegalArgumentException("존재하지 않는 병원 입니다. 다시 확인해주세요.");

        int updateResult = hospitalRepository.update(hospital);


        if (updateResult != 1) throw new IllegalArgumentException("병원 정보 수정 실패. 다시 시도해주세요.");

        hospitalSubInfoService.update(hospital.getUniqueId(), request.getSubjects());
    }



}
