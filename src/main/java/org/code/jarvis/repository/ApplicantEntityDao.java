package org.code.jarvis.repository;

import org.code.jarvis.model.core.Applicant;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by KimChheng on 5/18/2017.
 */
public interface ApplicantEntityDao extends EntityDao {

    Applicant saveOrUpdateApplicant(MultipartFile[] files, String json) throws Exception;

    List<Applicant> fetchApplicants(int offset, int limit) throws Exception;
}
