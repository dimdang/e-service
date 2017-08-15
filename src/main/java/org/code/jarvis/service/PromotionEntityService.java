package org.code.jarvis.service;

import org.code.jarvis.model.core.Promotion;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by KimChheng on 8/15/2017.
 */
public interface PromotionEntityService extends EntityService {

    Promotion saveOrUpdatePromotion(MultipartFile[] files, Promotion promotion) throws Exception;
}
