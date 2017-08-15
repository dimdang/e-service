package org.code.jarvis.service;

import org.code.jarvis.model.core.AbstractEntity;
import org.code.jarvis.model.core.Image;
import org.code.jarvis.repository.EntityDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.util.List;

/**
 * Created by KimChheng on 5/14/2017.
 */
public interface EntityService {

    EntityDao getDao();

    SessionFactory getSessionFactory();

    Session getCurrentSession();

    Connection getConnection();

    <T> T getEntityById(Long id, Class<T> clazz);

    <T> T loadEntityById(Long id, Class<T> clazz);

    <T> List<T> getEntityByCode(String code, Class<T> clazz);

    <T> List<T> list(Class<T> clazz);

    <T extends AbstractEntity> void save(T entity);

    <T extends AbstractEntity> void update(T entity);

    <T> void saveOrUpdate(T entity);

    <T extends AbstractEntity> void save(List<T> list);

    <T extends AbstractEntity> void update(List<T> list);

    <T> void saveOrUpdate(List<T> list);

    <T> void delete(T entity);

    void executeSQL(String sql);

    void flush();

    void clear();
}
