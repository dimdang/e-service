package org.code.jarvis.service;

import org.code.jarvis.component.ApplicationContextProvider;

/**
 * Created by KimChheng on 5/14/2017.
 */
public interface ServiceHelper {
    EntityService ENTITY_SERVICE = ApplicationContextProvider.getContext().getBean(EntityService.class);
    ProductEntityService PRODUCT_ENTITY_SERVICE = ApplicationContextProvider.getContext().getBean(ProductEntityService.class);
}
