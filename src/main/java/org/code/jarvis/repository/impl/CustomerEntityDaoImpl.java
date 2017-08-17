package org.code.jarvis.repository.impl;

import org.code.jarvis.model.core.Customer;
import org.code.jarvis.model.core.Image;
import org.code.jarvis.repository.CustomerEntityDao;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by KimChheng on 5/18/2017.
 */
@Repository
public class CustomerEntityDaoImpl extends AbstractEntityDao implements CustomerEntityDao {


    @Override
    public Customer saveOrUpdateCustomer(MultipartFile[] files, Customer customer) throws Exception {
        if (customer != null) {
            if (customer.getCreateDate() == null) customer.setCreateDate(new Date());
            if (customer.getUpdateDate() == null) customer.setUpdateDate(new Date());
            List<Image> images = new ArrayList<>(files.length);
            for (int i = 0; i < files.length; i++) {
                Image image = new Image();
                image.setType(files[i].getContentType());
                image.setName(files[i].getOriginalFilename());
                image.setBytes(files[i].getBytes());
                images.add(image);
            }
            customer.setImages(images);
            saveOrUpdate(customer);
        }
        return customer;
    }
}
