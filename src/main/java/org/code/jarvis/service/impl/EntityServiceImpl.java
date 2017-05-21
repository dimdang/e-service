package org.code.jarvis.service.impl;

import org.code.jarvis.repository.EntityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by KimChheng on 5/14/2017.
 */
@Service("entityService")
public class EntityServiceImpl extends AbstractEntityService {

    @Autowired
    private EntityDao entityDao;

    @Override
    public EntityDao getDao() {
        return entityDao;
    }
}
