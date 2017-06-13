package org.code.jarvis.repository;

import org.code.jarvis.model.core.Applicant;
import org.code.jarvis.model.core.Customer;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by KimChheng on 5/18/2017.
 */
public interface ApplicantEntityDao extends EntityDao {

    Customer saveOrUpdateCustomer(MultipartFile[] files, String json) throws Exception;

    Applicant saveOrUpdateApplicant(MultipartFile[] files, String json) throws Exception;

    List<Applicant> fetchApplicants(int offset, int limit) throws Exception;
}
