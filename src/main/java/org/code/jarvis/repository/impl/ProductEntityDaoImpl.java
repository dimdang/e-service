package org.code.jarvis.repository.impl;

import org.code.jarvis.model.core.*;
import org.code.jarvis.repository.ProductEntityDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by KimChheng on 5/16/2017.
 */
@Repository
public class ProductEntityDaoImpl extends AbstractEntityDao implements ProductEntityDao {

    @Override
    public Product saveOrUpdateProduct(MultipartFile[] files, Product json) throws Exception {
        Product product = null;
        ProductContact productContact, tmpContact;
        List<ProductImage> productImages = new ArrayList<>();
        if (json != null) {
            if (json.getId() == null) {
                product = json;
                productContact = json.getContact();
                productContact.setProduct(product);
                product.setCreateDate(new Date());
            } else {
                product = getEntityById(json.getId(), Product.class);
                productContact = product.getContact();
                tmpContact = json.getContact();
                product.setCode(json.getCode());
                product.setColor(json.getColor());
                product.setSize(json.getSize());
                product.setPrice(json.getPrice());
                product.setProductType(json.getProductType());
                productContact.setPhone1(tmpContact.getPhone1());
                productContact.setPhone2(tmpContact.getPhone2());
                productContact.setEmail(tmpContact.getEmail());
                productContact.setFacebook(tmpContact.getFacebook());
            }
            product.setUpdateDate(new Date());
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
        return product;
    }

    @Override
    public List<Product> fetchProducts(int offset, int limit, EProductType type) {
        Criteria criteria = getCurrentSession().createCriteria(Product.class);
        criteria.add(Restrictions.eq("productType", type));
        criteria.addOrder(Order.desc("updateDate"));
        criteria.setFirstResult((offset - 1) * limit);
        criteria.setMaxResults(limit);
        return criteria.list();
    }

    @Override
    public ProductImage getProductImage(long id) {
        Criteria criteria = getCurrentSession().createCriteria(ProductImage.class);
        criteria.add(Restrictions.eq("image.id", id));
        List<ProductImage> list = criteria.list();
        return list != null && !list.isEmpty() ? list.get(0) : null;
    }
}
