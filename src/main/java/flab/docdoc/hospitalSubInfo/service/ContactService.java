package flab.docdoc.hospitalSubInfo.service;

import flab.docdoc.hospitalSubInfo.domain.Contact;
import flab.docdoc.hospitalSubInfo.domain.HospitalSubject;

import java.util.List;

public interface ContactService {

    public void saveContacts(Long hospitalUniqueId, List<Contact> contacts);

}
