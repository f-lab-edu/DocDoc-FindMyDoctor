package flab.docdoc.hospital.service;

import flab.docdoc.hospital.domain.Hospital;
import flab.docdoc.hospital.repository.HospitalRepository;
import flab.docdoc.hospital.request.AddHospitalRequest;
import flab.docdoc.hospitalSubInfo.service.HospitalSubInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService{

    private final HospitalRepository hospitalRepository;
    private final HospitalSubInfoService hospitalSubInfoService;
 //   private final LocationService locationService;

     @Override
     @Transactional
     public void save(AddHospitalRequest request) {
         Hospital hospital = AddHospitalRequest.of(request);
         int hospitalSaveResult = hospitalRepository.save(hospital);

         if (hospitalSaveResult != 1 || hospital.getUniqueId() == null)
             throw new IllegalArgumentException("병원 등록 실패. 다시 시도해주세요.");

         hospitalSubInfoService.save(hospital.getUniqueId(), request.getContacts(), request.getSubjects());

     }
//    @Override
//    public Boolean isExistHospital(Long hospitalUniqueId) {
//        Hospital hospital = findByUniqueId(hospitalUniqueId);
//        if (hospital == null) {
//            return false;
//        }
//        return true;
//    }

//    private Hospital findByUniqueId(Long hospitalId) {
//        if (hospitalId == null) {
//            throw new IllegalArgumentException("병원 등록번호를 다시 확인해주세요.");
//        }
//        return hospitalRepository.findByUniqueId(hospitalId);
//    };




    //    private Hospital findByUniqueIdAndAdminUniqueId(Long hospitalId, Long memberUniqueId) {
//        if (hospitalId == null || memberUniqueId == null) {
//            throw new IllegalArgumentException("입력한 값을 다시 확인해주세요.");
//        }
//        return hospitalRepository.findByUniqueIdAndAdminUniqueId(hospitalId, memberUniqueId);
//    }

}
