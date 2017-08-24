package org.code.jarvis.service;

import org.code.jarvis.model.core.EProductType;
import org.code.jarvis.model.core.Product;
import org.code.jarvis.model.core.Promotion;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by KimChheng on 5/15/2017.
 */
public interface ProductEntityService extends EntityService {

    Product saveOrUpdateProduct(MultipartFile[] files, Product json) throws Exception;

    List<Product> fetchProducts(int offset, int limit, EProductType type);

    List<Promotion> fetchPromotion(int offset, int limit);

}
