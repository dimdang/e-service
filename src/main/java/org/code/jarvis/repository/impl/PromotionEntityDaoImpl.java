package org.code.jarvis.repository.impl;

import org.code.jarvis.model.core.Image;
import org.code.jarvis.model.core.Promotion;
import org.code.jarvis.repository.PromotionEntityDao;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by KimChheng on 8/15/2017.
 */
@Repository
public class PromotionEntityDaoImpl extends AbstractEntityDao implements PromotionEntityDao {


    @Override
    public Promotion saveOrUpdatePromotion(MultipartFile[] files, Promotion json) throws Exception {
        Promotion promotion = null;
        List<Image> images = new ArrayList<>();
        if (json != null) {
            if (json.getId() == null) {
                promotion = json;
                promotion.setCreateDate(new Date());
            } else {
                promotion = getEntityById(json.getId(), Promotion.class);
                promotion.setCode(json.getCode());
                promotion.setDesc(json.getDesc());
                images = promotion.getImages();
            }
            promotion.setUpdateDate(new Date());
            for (int i = 0; i < files.length; i++) {
                Image image = new Image();
                image.setType(files[i].getContentType());
                image.setName(files[i].getOriginalFilename());
                image.setBytes(files[i].getBytes());
                if (image.getType().equals(MediaType.IMAGE_JPEG_VALUE) || image.getType().equals(MediaType.IMAGE_PNG_VALUE))
                    images.add(image);
            }
            promotion.setImages(images);
            saveOrUpdate(promotion);
        }
        return promotion;
    }
}
