package org.code.jarvis.repository.impl;

import org.code.jarvis.model.core.AbstractEntity;
import org.code.jarvis.model.core.Image;
import org.code.jarvis.repository.EntityDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.util.List;

/**
 * Created by KimChheng on 5/13/2017.
 */
@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {java.lang.Exception.class})
public abstract class AbstractEntityDao implements EntityDao {

    private final Logger log = LoggerFactory.getLogger(AbstractEntityDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Connection getConnection() {
        return ((SessionImpl) getCurrentSession()).connection();
    }

    /**
     * It always hit the database and return the real object,
     * an object that represent the database row, not proxy.
     * If no row found , it return null.
     */
    @Override
    public <T> T getEntityById(Long id, Class<T> clazz) {
        T bean = null;
        if (id != null && id > 0 && clazz != null)
            bean = getCurrentSession().get(clazz, id);
        return bean;
    }

    /**
     * It will always return a “proxy” (Hibernate term) without hitting the database.
     * it just look like a temporary fake object.
     * If no row found , it will throws an ObjectNotFoundException.
     */
    @Override
    public <T> T loadEntityById(Long id, Class<T> clazz) {
        T bean = null;
        if (id != null && id > 0 && clazz != null)
            bean = getCurrentSession().load(clazz, id);
        return bean;
    }

    @Override
    public <T> List<T> getEntityByCode(String code, Class<T> clazz) {
        if (code != null && !code.isEmpty() && clazz != null) {
            Criteria criteria = getCurrentSession().createCriteria(clazz);
            criteria.add(Restrictions.eq("code", code));
            return criteria.list();
        }
        return null;
    }

    @Override
    public <T> List<T> list(Class<T> clazz) {
        if (clazz != null) {
            Criteria criteria = getCurrentSession().createCriteria(clazz);
            criteria.addOrder(Order.asc("id"));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            return criteria.list();
        }
        return null;
    }

    @Override
    public <T extends AbstractEntity> T save(T entity) {
        if (entity != null && entity.getId() == null)
            return (T) getCurrentSession().save(entity);
        return entity;
    }

    @Override
    public <T extends AbstractEntity> void update(T entity) {
        if (entity != null && entity.getId() != null)
            getCurrentSession().update(entity);
    }

    @Override
    public <T> void saveOrUpdate(T entity) {
        if (entity != null)
            getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public <T extends AbstractEntity> List<T> save(List<T> list) {
        if (list != null)
            for (T entity : list)
                save(entity);
        return list;
    }

    @Override
    public <T extends AbstractEntity> void update(List<T> list) {
        if (list != null)
            for (T entity : list)
                update(entity);
    }

    @Override
    public <T> void saveOrUpdate(List<T> list) {
        if (list != null)
            for (T entity : list)
                saveOrUpdate(entity);
    }

    @Override
    public <T> void delete(T entity) {
        if (entity != null)
            getCurrentSession().delete(entity);
    }

    @Override
    public Image getImage(Long id) {
        if (id != null && id > 0)
            return getEntityById(id, Image.class);
        return null;
    }

    @Override
    public void flush() {
        getCurrentSession().flush();
    }

    @Override
    public void clear() {
        getCurrentSession().clear();
    }


}
