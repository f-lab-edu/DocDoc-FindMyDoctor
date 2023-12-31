package flab.docdoc.hospital.service;

import flab.docdoc.hospital.domain.Hospital;
import flab.docdoc.hospital.repository.HospitalRepository;
import flab.docdoc.hospital.request.AddHospitalRequest;
import flab.docdoc.hospital.request.UpdateHospitalRequest;
import flab.docdoc.hospital.response.HospitalResponse;
import flab.docdoc.hospitalSubInfo.domain.SubInfo;
import flab.docdoc.hospitalSubInfo.service.HospitalSubInfoService;
import flab.docdoc.review.domain.HospitalStatistics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class HospitalServiceImpl implements HospitalService{

    private final HospitalRepository hospitalRepository;
    private final HospitalSubInfoService hospitalSubInfoService;

    @Override
    public Optional<Hospital> findByUniqueId(String hospitalUniqueId) {
        Assert.notNull(hospitalUniqueId, "hospitalUniqueId must not be null");
        return Optional.ofNullable(hospitalRepository.findByUniqueId(hospitalUniqueId));
    }

    @Override
    @Transactional
    public void save(AddHospitalRequest request) {
        findByUniqueId(request.getUniqueId()).ifPresent(a -> {throw new IllegalArgumentException("이미 존재하는 병원 입니다.");});

        Hospital hospital = AddHospitalRequest.of(request);
        int saveResult = hospitalRepository.save(hospital);

        if (saveResult != 1 || hospital.getUniqueId() == null) {
            throw new IllegalArgumentException("병원 등록 실패. 다시 시도해주세요.");
        }

        hospitalSubInfoService.save(hospital.getUniqueId(), request.getSubjects());
    }

    @Override
    @Transactional
    public void update(UpdateHospitalRequest request) {
        findByUniqueId(request.getUniqueId()).orElseThrow(() -> {throw new IllegalArgumentException("존재하지 않는 병원 입니다. 다시 확인해주세요.");});

        Hospital hospital = UpdateHospitalRequest.of(request);

        int updateResult = hospitalRepository.update(hospital);
        if (updateResult != 1) {
            throw new IllegalArgumentException("병원 정보 수정 실패. 다시 시도해주세요.");
        }

        hospitalSubInfoService.update(hospital.getUniqueId(), request.getSubjects(), request.getWorkdays(), request.getHolidays(), request.getTags());
    }

    @Override
    public void updateAdmin(final String hospitalUniqueId, final Long memberUniqueId) {
        if (hospitalUniqueId == null) {
            throw new IllegalArgumentException("입력 정보 오류. 병원 정보를 다시 확인해주세요.");
        }
        int updateResult = hospitalRepository.updateAdmin(hospitalUniqueId, memberUniqueId);

        if (updateResult != 1) {
            throw new IllegalArgumentException("병원 정보 수정실패. 다시 시도해주세요.");
        }
    }

    @Override
    public void updateStatistics(HospitalStatistics statistics) {
        int updateResult = hospitalRepository.updateStatistics(statistics);
        if (updateResult != 1) {
            throw new IllegalArgumentException("업데이트 실패! 다시 시도해주세요.");
        }
    }

    @Override
    public HospitalResponse getHospitalResponse(String hospitalUniqueId) {
        if (hospitalUniqueId == null) {
            throw new IllegalArgumentException("입력 값을 다시 확인해주세요.");
        }

        HospitalResponse response = hospitalRepository.findHospitalResponseByUniqueId(hospitalUniqueId);
        if (response == null) {
            throw new IllegalArgumentException("존재하지 않는 병원 입니다. 다시 확인해주세요.");
        }

        SubInfo subInfo = hospitalSubInfoService.findByHospitalUniqueId(hospitalUniqueId);

        return HospitalResponse.of(response, subInfo);
    }


}
