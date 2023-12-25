package flab.docdoc.hospital.service;

import flab.docdoc.fake.FakeHospitalRepository;
import flab.docdoc.fake.FakeHospitalSubInfoService;
import flab.docdoc.hospital.domain.Hospital;
import flab.docdoc.hospital.repository.HospitalRepository;
import flab.docdoc.hospital.request.AddHospitalRequest;
import flab.docdoc.hospital.request.UpdateHospitalRequest;
import flab.docdoc.hospitalSubInfo.domain.Subject;
import flab.docdoc.review.domain.HospitalStatistics;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

class HospitalServiceTest {

    private HospitalService hospitalService;

    @BeforeEach
    void init() {
        HospitalRepository hospitalRepository = new FakeHospitalRepository();
        this.hospitalService = new HospitalServiceImpl(hospitalRepository, new FakeHospitalSubInfoService());

        Set<Subject> subjects = new HashSet<>();
        subjects.add(Subject.IM);

        Hospital hospital = Hospital.builder()
                .uniqueId("already")
                .businessName("이미 동록된 병원")
                .tel("010-010-010")
                .sidoNm("서울특별시")
                .sgguNm("강남구")
                .addr("도봉구 무슨대로 12번길")
                .adminId("aaa")
                .star(0L)
                .reviewCount(0L)
                .build();

        hospitalRepository.save(hospital);
    }

    @Test
    void save_는_병원을_등록할_수_있다() {
        //given
        String uniqueId = "new";
        AddHospitalRequest request = AddHospitalRequest.builder()
                .uniqueId(uniqueId)
                .businessName("새병원")
                .tel("010-010-010")
                .sidoNm("서울특별시")
                .sgguNm("강남구")
                .addr("도봉구 무슨대로 12번길")
                .build();

        //when
        hospitalService.save(request);

        Hospital existHospital = hospitalService.findByUniqueId(request.getUniqueId()).get();

        //then
        Assertions.assertThat(existHospital.getUniqueId()).isEqualTo(request.getUniqueId());
    }

    @Test
    void save_는_이미_등록된_uniqueId_를_파라미터로_받으면_예외를_던진다() {
        //given
        String uniqueId = "already";
        AddHospitalRequest request = AddHospitalRequest.builder()
                .uniqueId(uniqueId)
                .businessName("새병원")
                .tel("010-010-010")
                .sidoNm("서울특별시")
                .sgguNm("강남구")
                .addr("도봉구 무슨대로 12번길")
                .build();

        //when

        //then
        Assertions.assertThatThrownBy(() -> {
            hospitalService.save(request);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void findByUniqueId_Optional_을_반환한다() {
        //given
        String uniqueId = "already";

        //when
        Optional<Hospital> existHospital = hospitalService.findByUniqueId(uniqueId);

        //then
        Assertions.assertThat(existHospital).isInstanceOf(Optional.class);
    }

    @Test
    void findByUniqueId_는_등록된_회원을_찾아올_수_있다() {
        //given
        String uniqueId = "already";

        //when
        Hospital existHospital = hospitalService.findByUniqueId(uniqueId).get();

        //then
        Assertions.assertThat(existHospital.getUniqueId()).isEqualTo("already");
    }

    @Test
    void findByUniqueId_는_등록되지_않은_회원은_예외를_던진다() {
        //given
        String uniqueId = "notExist";

        //when

        //thwn
        Assertions.assertThatThrownBy(() -> {
            hospitalService.findByUniqueId(uniqueId).get();
        }).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void update_는_병원을_수정할_수_있다() {
        //given
        String memberId = "aaa";
        String uniqueId = "already";
        UpdateHospitalRequest request = UpdateHospitalRequest.builder()
                .uniqueId(uniqueId)
                .businessName("이미 등록된 병원(수정)")
                .tel("010-010-010")
                .sidoNm("서울특별시")
                .sgguNm("강남구")
                .addr("도봉구 무슨대로 12번길")
                .build();

        //when
        hospitalService.update(request, memberId);

        Hospital existHospital = hospitalService.findByUniqueId(request.getUniqueId()).get();

        //then
        Assertions.assertThat(existHospital.getBusinessName()).isEqualTo("이미 등록된 병원(수정)");
    }

    @Test
    void update_는_존재하지_않는_uniqueId_를_파라미터로_받으면_예외를_던진다() {
        //given
        String memberId = "aaa";
        String uniqueId = "notExist";
        UpdateHospitalRequest request = UpdateHospitalRequest.builder()
                .uniqueId(uniqueId)
                .businessName("이미 등록된 병원(수정)")
                .tel("010-010-010")
                .sidoNm("서울특별시")
                .sgguNm("강남구")
                .addr("도봉구 무슨대로 12번길")
                .build();

        //when

        //then
        Assertions.assertThatThrownBy(() -> {
            hospitalService.update(request, memberId);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void update_는_병원에_등록된_adminId_가_loginId_파라미터와_다르면_예외를_던진다() {
        //given
        String memberId = "notAAA";
        String uniqueId = "already";
        UpdateHospitalRequest request = UpdateHospitalRequest.builder()
                .uniqueId(uniqueId)
                .businessName("이미 등록된 병원(수정)")
                .tel("010-010-010")
                .sidoNm("서울특별시")
                .sgguNm("강남구")
                .addr("도봉구 무슨대로 12번길")
                .build();

        //when

        //then
        Assertions.assertThatThrownBy(() -> {
            hospitalService.update(request, memberId);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void updateAdmin_은_병원_관리자를_수정할_수_있다() {
        //given
        String memberId = "aaa";
        String uniqueId = "new";
        AddHospitalRequest request = AddHospitalRequest.builder()
                .uniqueId(uniqueId)
                .businessName("새병원")
                .tel("010-010-010")
                .sidoNm("서울특별시")
                .sgguNm("강남구")
                .addr("도봉구 무슨대로 12번길")
                .build();
        hospitalService.save(request);

        //when
        hospitalService.updateAdmin(uniqueId, memberId);

        Hospital existHospital = hospitalService.findByUniqueId(uniqueId).get();

        //then
        Assertions.assertThat(existHospital.getAdminId()).isEqualTo(memberId);
    }

    @Test
    void updateStatistics_는_병원_평점을_수정할_수_있다() {
        //given
        String memberId = "aaa";
        String uniqueId = "already";
        Long star = 5L;

        //when
        hospitalService.updateStatistics(HospitalStatistics.HospitalStatisticsBuilder()
                                        .hospitalUniqueId(uniqueId)
                                        .star(star)
                                        .reviewCount(1L)
                                        .build());

        Hospital existHospital = hospitalService.findByUniqueId(uniqueId).get();

        //then
        Assertions.assertThat(existHospital.getStar()).isEqualTo(5L);
        Assertions.assertThat(existHospital.getReviewCount()).isEqualTo(1L);
    }
}