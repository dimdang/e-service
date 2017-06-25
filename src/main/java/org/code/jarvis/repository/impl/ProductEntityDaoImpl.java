package org.code.jarvis.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import flexjson.JSONDeserializer;
import org.code.jarvis.model.core.Image;
import org.code.jarvis.model.core.Product;
import org.code.jarvis.model.core.ProductContact;
import org.code.jarvis.model.core.ProductImage;
import org.code.jarvis.repository.ProductEntityDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by KimChheng on 5/16/2017.
 */
@Repository
public class ProductEntityDaoImpl extends AbstractEntityDao implements ProductEntityDao {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JSONDeserializer<Map<String, Object>> deserializer = new JSONDeserializer<>();

    @Override
    public Product saveOrUpdateProduct(MultipartFile[] files, String json) throws Exception {
        Product product = null, tmp = null;
        ProductContact productContact = null;
        if (json != null && !json.isEmpty()) {
            product = objectMapper.readValue(json, Product.class);
            if (product.getCreateDate() == null) product.setCreateDate(new Date());
            if (product.getUpdateDate() == null) product.setUpdateDate(new Date());
            if (product.getId() != null && product.getId() > 0) {
                tmp = product;
                product = getEntityById(product.getId(), Product.class);
                product.setCode(tmp.getCode());
                product.setSize(tmp.getSize());
                product.setPrice(tmp.getPrice());
                product.setColor(tmp.getColor());
                product.setUpdateDate(new Date());
            }
            productContact = product.getContact();
            if (productContact != null) {
                if (productContact.getProduct() == null)
                    productContact.setProduct(product);
                if (productContact.getId() != null) {
                    productContact.setPhone1(tmp.getContact().getPhone1());
                    productContact.setPhone2(tmp.getContact().getPhone2());
                    productContact.setPhone3(tmp.getContact().getPhone3());
                    productContact.setEmail(tmp.getContact().getEmail());
                    productContact.setFacebook(tmp.getContact().getFacebook());
                }
            }
            saveOrUpdate(product);
            saveOrUpdateProductImage(product, files, tmp);
        }
        return product;
    }

    private void saveOrUpdateProductImage(Product product, MultipartFile[] files, Product tmp) throws IOException {
        //List<Image> list = FileUtil.saveFiles(product.getId(), files);
        if (tmp != null && tmp.getProductImages() != null && !tmp.getProductImages().isEmpty()) {
            for (ProductImage productImage : tmp.getProductImages())
                delete(productImage);
        }
        List<ProductImage> productImages = product.getProductImages();
        if (productImages == null)
            productImages = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            ProductImage productImage = new ProductImage();
            Image image = new Image();
            image.setType(files[i].getContentType());
            image.setName(files[i].getOriginalFilename());
            image.setBytes(files[i].getBytes());
            productImage.setImage(image);
            productImage.setProduct(product);
            productImages.add(productImage);
        }
        product.setProductImages(productImages);
        saveOrUpdate(product);
    }

    @Override
    public List<Product> fetchProducts(int offset, int limit) {
        Criteria criteria = getCurrentSession().createCriteria(Product.class);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.addOrder(Order.desc("updateDate"));
        criteria.setFirstResult((offset - 1) * limit);
        criteria.setMaxResults(limit);
        return criteria.list();
    }
}
