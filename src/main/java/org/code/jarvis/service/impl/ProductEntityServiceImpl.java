package org.code.jarvis.service.impl;

import org.code.jarvis.model.core.EProductType;
import org.code.jarvis.model.core.Product;
import org.code.jarvis.model.core.ProductImage;
import org.code.jarvis.repository.EntityDao;
import org.code.jarvis.repository.ProductEntityDao;
import org.code.jarvis.service.ProductEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by KimChheng on 5/15/2017.
 */
@Service
public class ProductEntityServiceImpl extends AbstractEntityService implements ProductEntityService {

    @Autowired
    private ProductEntityDao productEntityDao;

    @Override
    public EntityDao getDao() {
        return productEntityDao;
    }

    @Override
    public Product saveOrUpdateProduct(MultipartFile[] files, Product json) throws Exception {
        return productEntityDao.saveOrUpdateProduct(files, json);
    }

    @Override
    public List<Product> fetchProducts(int offset, int limit, EProductType type) {
        return productEntityDao.fetchProducts(offset, limit, type);
    }

    @Override
    public ProductImage getProductImage(long id) {
        return productEntityDao.getProductImage(id);
    }

}
