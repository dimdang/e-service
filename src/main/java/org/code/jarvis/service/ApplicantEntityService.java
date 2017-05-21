package org.code.jarvis.service;

import org.code.jarvis.model.core.Applicant;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by KimChheng on 5/19/2017.
 */
public interface ApplicantEntityService extends EntityService {

    Applicant saveOrUpdateApplicant(MultipartFile[] files, String json) throws Exception;

    List<Applicant> fetchApplicants(int offset, int limit) throws Exception;
}
