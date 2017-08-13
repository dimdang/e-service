package org.code.jarvis.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import flexjson.JSONDeserializer;
import org.code.jarvis.model.core.Customer;
import org.code.jarvis.model.core.CustomerImage;
import org.code.jarvis.model.core.Image;
import org.code.jarvis.model.core.Product;
import org.code.jarvis.repository.CustomerEntityDao;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by KimChheng on 5/18/2017.
 */
@Repository
public class CustomerEntityDaoImpl extends AbstractEntityDao implements CustomerEntityDao {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JSONDeserializer<Map<String, Object>> deserializer = new JSONDeserializer<>();

    @Override
    public Customer saveOrUpdateCustomer(MultipartFile[] files, String json) throws Exception {
        Customer customer = null;
        if (json != null && !json.isEmpty()) {
            customer = objectMapper.readValue(json, Customer.class);
            if (customer != null) {
                if (customer.getCreateDate() == null) customer.setCreateDate(new Date());
                if (customer.getUpdateDate() == null) customer.setUpdateDate(new Date());
                Product product = loadEntityById(customer.getProductId(), Product.class);
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

}
