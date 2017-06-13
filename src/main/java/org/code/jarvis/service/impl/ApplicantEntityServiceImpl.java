package org.code.jarvis.service.impl;

import org.code.jarvis.model.core.Applicant;
import org.code.jarvis.model.core.Customer;
import org.code.jarvis.repository.ApplicantEntityDao;
import org.code.jarvis.repository.EntityDao;
import org.code.jarvis.service.ApplicantEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by KimChheng on 5/19/2017.
 */
@Service
public class ApplicantEntityServiceImpl extends AbstractEntityService implements ApplicantEntityService {

    @Autowired
    private ApplicantEntityDao applicantDao;

    @Override
    public Customer saveOrUpdateCustomer(MultipartFile[] files, String json) throws Exception {
        return applicantDao.saveOrUpdateCustomer(files, json);
    }

    @Override
    public Applicant saveOrUpdateApplicant(MultipartFile[] files, String json) throws Exception {
        return applicantDao.saveOrUpdateApplicant(files, json);
    }

    @Override
    public List<Applicant> fetchApplicants(int offset, int limit) throws Exception {
        return applicantDao.fetchApplicants(offset, limit);
    }

    @Override
    public EntityDao getDao() {
        return applicantDao;
    }
}
