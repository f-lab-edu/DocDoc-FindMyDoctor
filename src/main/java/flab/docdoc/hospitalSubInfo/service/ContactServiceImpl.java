package flab.docdoc.hospitalSubInfo.service;

import flab.docdoc.hospitalSubInfo.domain.Contact;
import flab.docdoc.hospitalSubInfo.domain.HospitalSubject;
import flab.docdoc.hospitalSubInfo.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Override
    public void saveContacts(Long hospitalUniqueId, List<Contact> contacts) {
        List<Contact> newContacts = Contact.of(hospitalUniqueId, contacts);

        Long count = contactRepository.countByHospitalUniqueId(hospitalUniqueId);
        if (count > 0) {
            int deleteCount = contactRepository.deleteByHospitalUniqueId(hospitalUniqueId);
            if (count != deleteCount) throw new IllegalArgumentException("삭제 오류");
        }

        int insertCount = contactRepository.saveContacts(newContacts);
        if (insertCount < 0 || insertCount != contacts.size()) throw new IllegalArgumentException("입력 오류! 연락 정보는 1 ~ 3개 까지 입력가능합니다.");
    }



}
