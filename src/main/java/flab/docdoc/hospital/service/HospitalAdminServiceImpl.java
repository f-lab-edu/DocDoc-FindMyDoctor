package flab.docdoc.hospital.service;

import flab.docdoc.hospital.domain.Hospital;
import flab.docdoc.hospital.request.UpdateHospitalAdminRequest;
import flab.docdoc.member.domain.Member;
import flab.docdoc.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HospitalAdminServiceImpl implements HospitalAdminService{

    private final HospitalService hospitalService;
    private final MemberService memberService;

    @Transactional
    @Override
    public void addHospitalAdmin(UpdateHospitalAdminRequest request, final String loginId) {
        Member existMember = memberService.findByLoginId(loginId)
                .orElseThrow(() -> {throw new IllegalArgumentException("존재하지 않는 회원 입니다. 다시 확인해주세요");});

        if (existMember.getRole() == Member.Role.ADMIN) {
            throw new IllegalArgumentException("해당 회원은 이미 관리자 입니다. 다시 확인해주세요");
        }

        Hospital existHospital = hospitalService.findByUniqueId(request.getHospitalUniqueId())
                .orElseThrow(() -> {throw new IllegalArgumentException("존재하지 않는 병원 입니다. 다시 확인해주세요");});

        if (existHospital.getAdminId() != null) {
            throw new IllegalArgumentException("해당 병원은 이미 관리자가 존재합니다. 다시 확인해주세요.");
        }

        hospitalService.updateAdmin(request.getHospitalUniqueId(), loginId);
        memberService.updateMemberRole(loginId, Member.Role.ADMIN);
    }

    @Transactional
    @Override
    public void deleteHospitalAdmin(UpdateHospitalAdminRequest request, final String loginId) {
        Member existMember = memberService.findByLoginId(loginId)
                .orElseThrow(() -> {throw new IllegalArgumentException("존재하지 않는 회원 입니다. 다시 확인해주세요");});

        if (existMember.getRole() == Member.Role.PUBLIC) {
            throw new IllegalArgumentException("해당 회원은 관리자가 아닙니다. 다시 확인해주세요");
        }

        Hospital existHospital = hospitalService.findByUniqueId(request.getHospitalUniqueId())
                .orElseThrow(() -> {throw new IllegalArgumentException("존재하지 않는 병원 입니다. 다시 확인해주세요");});

        if (existHospital.getAdminId() == null) {
            throw new IllegalArgumentException("해당 병원은 관리자가 존재하지 않습니다. 다시 확인해주세요.");
        }

        if (!existHospital.getAdminId().equals(existMember.getLoginId())) {
            throw new IllegalArgumentException("해당 병원의 관리자와 회원이 일치하지 않습니다. 다시 확인해주세요.");
        }

        hospitalService.updateAdmin(request.getHospitalUniqueId(), null);
        memberService.updateMemberRole(loginId, Member.Role.PUBLIC);
    }


}
