package org.oguz.orm.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.common.base.Preconditions;

@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:persistence-mysql.properties" })
@ComponentScan({ "org.oguz.orm" })
public class PersistenceConfig {

	@Autowired
	private Environment env;

	public PersistenceConfig() {
		super();
	}

	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".html");
		return viewResolver;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(restDataSource());
		sessionFactory
				.setPackagesToScan(new String[] { "org.oguz.orm.entities" });
		sessionFactory.setHibernateProperties(hibernateProperties());

		return sessionFactory;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(
			final SessionFactory sessionFactory) {
		final HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean
	public DataSource restDataSource() {
		final BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(Preconditions.checkNotNull(env
				.getProperty("jdbc.driverClassName")));
		dataSource.setUrl(Preconditions.checkNotNull(env
				.getProperty("jdbc.url")));
		dataSource.setUsername(Preconditions.checkNotNull(env
				.getProperty("jdbc.user")));
		dataSource.setPassword(Preconditions.checkNotNull(env
				.getProperty("jdbc.pass")));

		return dataSource;
	}

	final Properties hibernateProperties() {
		final Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto",
				env.getProperty("hibernate.hbm2ddl.auto"));
		hibernateProperties.setProperty("hibernate.dialect",
				env.getProperty("hibernate.dialect"));

		hibernateProperties.setProperty("hibernate.show_sql",
				env.getProperty("hibernate.show_sql"));
		hibernateProperties.setProperty("hibernate.format_sql",
				env.getProperty("hibernate.format_sql"));
		hibernateProperties.setProperty(
				"hibernate.globally_quoted_identifiers",
				env.getProperty("hibernate.show_sq"));

		return hibernateProperties;
	}

}
