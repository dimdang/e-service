package org.code.jarvis.repository;

import org.code.jarvis.model.core.Promotion;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by KimChheng on 8/15/2017.
 */
public interface PromotionEntityDao extends EntityDao {

    Promotion saveOrUpdatePromotion(MultipartFile[] files, Promotion json) throws Exception;
}
