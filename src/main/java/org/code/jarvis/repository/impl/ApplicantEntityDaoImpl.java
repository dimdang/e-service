package org.code.jarvis.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import flexjson.JSONDeserializer;
import org.code.jarvis.model.core.*;
import org.code.jarvis.model.refdata.*;
import org.code.jarvis.model.request.RequestApplicant;
import org.code.jarvis.model.request.RequestCustomer;
import org.code.jarvis.model.request.RequestPerson;
import org.code.jarvis.repository.ApplicantEntityDao;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by KimChheng on 5/18/2017.
 */
@Repository
public class ApplicantEntityDaoImpl extends AbstractEntityDao implements ApplicantEntityDao {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JSONDeserializer<Map<String, Object>> deserializer = new JSONDeserializer<>();

    @Override
    public Customer saveOrUpdateCustomer(MultipartFile[] files, String json) throws Exception {
        RequestCustomer requestCustomer = null;
        Customer customer = null;
        if (json != null && !json.isEmpty()) {
            requestCustomer = objectMapper.readValue(json, RequestCustomer.class);
            if (requestCustomer != null) {
                customer = new Customer(requestCustomer);
                Product product = loadEntityById(new Long(requestCustomer.getProductId()), Product.class);
                customer.setProduct(product);
                if (files != null && files.length > 0) {
                    List<CustomerImage> customerImages = new ArrayList<>(files.length);
                    for (int i = 0; i < files.length; i++) {
                        CustomerImage customerImage = new CustomerImage();
                        Image image = new Image();
                        image.setType(files[i].getContentType());
                        image.setName(files[i].getOriginalFilename());
                        image.setBytes(files[i].getBytes());
                        customerImage.setImage(image);
                        customerImage.setCustomer(customer);
                        customerImages.add(customerImage);
                    }
                    customer.setCustomerImages(customerImages);
                }
                saveOrUpdate(customer);
            }
        }
        return customer;
    }

    @Override
    public Applicant saveOrUpdateApplicant(MultipartFile[] files, String json) throws Exception {
        RequestApplicant requestApplicant = null;
        Applicant applicant = null;
        List<Person> persons = null;
        if (json != null && !json.isEmpty()) {
            requestApplicant = objectMapper.readValue(json, RequestApplicant.class);
            if (requestApplicant != null) {
                List<RequestPerson> requestPersons = requestApplicant.getPersons();
                if (requestPersons != null) {
                    applicant = new Applicant();
                    persons = new ArrayList<>(requestPersons.size());
                    for (RequestPerson requestPerson : requestPersons) {
                        applicant.setOther(requestApplicant.getOther());
                        applicant.setDate(requestApplicant.getDate());
                        Person person = new Person(requestPerson);
                        person.setGender(loadEntityById(new Long(requestPerson.getGender()), Gender.class));
                        Address address = new Address();
                        address.setProvince(loadEntityById(new Long(requestApplicant.getProvince()), Province.class));
                        address.setCommune(loadEntityById(new Long(requestApplicant.getCommune()), Commune.class));
                        address.setDistrict(loadEntityById(new Long(requestApplicant.getDistrict()), District.class));
                        address.setVillage(loadEntityById(new Long(requestApplicant.getVillage()), Village.class));
                        applicant.setAddress(address);
                        PersonContact personContact = new PersonContact(requestApplicant);
                        personContact.setApplicant(applicant);
                        applicant.setPersonContant(personContact);
                        person.setApplicant(applicant);
                        persons.add(person);
                    }
                    applicant.setPersons(persons);
                    ApplicantProductDetail applicantProductDetail = new ApplicantProductDetail();
                    applicantProductDetail.setProduct(loadEntityById(new Long(requestApplicant.getProductId()), Product.class));
                    applicantProductDetail.setApplicant(applicant);
                    applicant.setApplicantProductDetail(applicantProductDetail);
                    saveOrUpdate(applicant);
                    saveOrUpdateApplicantImage(files, applicant);
                }
            }
        }
        return applicant;
    }

    private void saveOrUpdateApplicantImage(MultipartFile[] files, Applicant applicant) throws IOException {
        if (files != null) {
            List<ApplicantImage> applicantImages = new ArrayList<>(files.length);
            for (int i = 0; i < files.length; i++) {
                ApplicantImage applicantImage = new ApplicantImage();
                Image image = new Image();
                image.setType(files[i].getContentType());
                image.setName(files[i].getOriginalFilename());
                image.setBytes(files[i].getBytes());
                applicantImage.setImage(image);
                applicantImage.setApplicant(applicant);
                applicantImages.add(applicantImage);
            }
            applicant.setApplicantImages(applicantImages);
            saveOrUpdate(applicant);
        }
    }

    @Override
    public List<Applicant> fetchApplicants(int offset, int limit) throws Exception {
        return null;
    }
}
