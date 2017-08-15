package org.code.jarvis.service.impl;

import org.code.jarvis.model.core.Promotion;
import org.code.jarvis.repository.EntityDao;
import org.code.jarvis.repository.PromotionEntityDao;
import org.code.jarvis.service.PromotionEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by KimChheng on 8/15/2017.
 */
@Service
public class PromotionEntityServiceImpl extends AbstractEntityService implements PromotionEntityService {

    @Autowired
    private PromotionEntityDao dao;

    @Override
    public Promotion saveOrUpdatePromotion(MultipartFile[] files, Promotion promotion) throws Exception {
        return dao.saveOrUpdatePromotion(files, promotion);
    }

    @Override
    public EntityDao getDao() {
        return dao;
    }
}
