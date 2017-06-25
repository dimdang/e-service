package org.code.jarvis.repository;

import org.code.jarvis.model.core.Customer;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by KimChheng on 5/18/2017.
 */
public interface CustomerEntityDao extends EntityDao {

    Customer saveOrUpdateCustomer(MultipartFile[] files, String json) throws Exception;

}
