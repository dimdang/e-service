package org.code.jarvis.service;

import org.code.jarvis.model.core.Customer;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by KimChheng on 5/19/2017.
 */
public interface CustomerEntityService extends EntityService {

    Customer saveOrUpdateCustomer(MultipartFile[] files, Customer customer) throws Exception;


}
