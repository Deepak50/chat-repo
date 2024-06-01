//package com.prj.chatapp.config;
//
//import javax.sql.DataSource;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//@Configuration
//public class HibernateConfiguration {
//
//	private static final Logger LOGGER = LoggerFactory.getLogger(HibernateConfiguration.class);
//
//	@Autowired
//	private Environment environment;
//	private String userAuth;
//	private String pwd;
//
//	public HibernateConfiguration() {
//
//
//	}
//
//	@SuppressWarnings("rawtypes")
//	@Bean(name = "datasource")
//	public DataSource dataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
//
//		
//		dataSource.setUrl(environment.getRequiredProperty("h2.url"));
//		dataSource.setUsername(environment.getRequiredProperty("h2.user"));
//		dataSource.setPassword(environment.getRequiredProperty("h2.password"));
//
//		// dataSource.setUrl(environment.getProperty("hana.url"));
//		// dataSource.setUsername(environment.getProperty("hana.user"));
//		// dataSource.setPassword(environment.getProperty("hana.password"));
//
//		return dataSource;
//	}
//
////	@Bean
////	public Properties hibernateProperties() {
////		Properties properties = new Properties();
////		properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
////		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
////		properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
////		properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
////		properties.put("hibernate.jdbc.batch_size", environment.getRequiredProperty("hibernate.jdbc.batch_size"));
////		properties.put("hibernate.cache.use_second_level_cache",
////				environment.getRequiredProperty("hibernate.cache.use_second_level_cache"));
////		properties.put("hibernate.order_inserts", Boolean.TRUE);
////		properties.put("hibernate.order_updates", Boolean.TRUE);
////		return properties;
////	}
//
////	@Bean(name = "session1")
////	public LocalSessionFactoryBean sessionFactory() {
////		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
////		LOGGER.error("sessiondb got executed");
////		sessionFactory.setDataSource(dataSource());
////		sessionFactory.setPackagesToScan(new String[] { "com.slb.cg" });
////		sessionFactory.setHibernateProperties(hibernateProperties());
////		return sessionFactory;
////	}
//
////	@Autowired
////	@Bean(name = "slbdbtx")
////	public HibernateTransactionManager transactionManager() {
////		HibernateTransactionManager txManager = new HibernateTransactionManager();
////		txManager.setSessionFactory(sessionFactory().getObject());
////		return txManager;
////	}
//
//}
//
