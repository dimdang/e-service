package org.code.jarvis.repository;

import org.code.jarvis.model.core.EProductType;
import org.code.jarvis.model.core.Product;
import org.code.jarvis.model.core.ProductImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by KimChheng on 5/16/2017.
 */
public interface ProductEntityDao extends EntityDao {

    Product saveOrUpdateProduct(MultipartFile[] files, Product json) throws Exception;

    List<Product> fetchProducts(int offset, int limit, EProductType type);

    ProductImage getProductImage(long id);
}
