package org.code.jarvis.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by KimChheng on 5/8/2017.
 */
@Configuration
@EnableTransactionManagement
@PropertySource(value = {"classpath:/config/database.properties",
        "classpath:/config/hibernate.properties", "classpath:/fcm/fcm.properties"})
public class WebConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource getDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(environment.getProperty("jarvis.db.driver"));
        ds.setUrl(environment.getProperty("jarvis.db.url"));
        ds.setUsername(environment.getProperty("jarvis.db.username"));
        ds.setPassword(environment.getProperty("jarvis.db.password"));
        return ds;
    }

    @Bean
    @Autowired
    public LocalSessionFactoryBean getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan(new String[]{"org.code.jarvis.model"});
        sessionFactory.setHibernateProperties(getHibernateProperties());
        return sessionFactory;
    }

    @Bean(name = "transactionManager")
    @Autowired
    public HibernateTransactionManager geTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        return transactionManager;
    }

    public Properties getHibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", environment.getProperty("jarvis.hibernate.dialect"));
        hibernateProperties.put("hibernate.hbm2ddl.auto", environment.getProperty("jarvis.hibernate.hbm2ddl.auto"));
        hibernateProperties.put("hibernate.show_sql", environment.getProperty("jarvis.hibernate.show_sql"));
        //hibernateProperties.put("hibernate.current_session_context_class", environment.getProperty("jarvis.hibernate.current_session_context_class"));
        //hibernateProperties.put("hibernate.autocommit", environment.getProperty("jarvis.hibernate.autocommit"));
        //hibernateProperties.put("org.hibernate.flushMode", environment.getProperty("jarvis.org.hibernate.flushMode"));
        //hibernateProperties.put("hibernate.use_sql_comments", environment.getProperty("jarvis.hibernate.use_sql_comments"));
        //hibernateProperties.put("hibernate.generate_statistics", environment.getProperty("jarvis.hibernate.generate_statistics"));
        //hibernateProperties.put("hibernate.format_sql",environment.getProperty("jarvis.hibernate.format_sql"));
        return hibernateProperties;
    }
}
