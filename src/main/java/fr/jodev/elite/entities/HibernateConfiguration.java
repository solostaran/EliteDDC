package fr.jodev.elite.entities;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.jodev.elite.DB_Parameters;

@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {

	private static DB_Parameters params;
	private Properties properties = null;

	static {
		params = null;
		try {
			File config = new File("dbconfig-h2.json");
			System.out.println("Reading database configuration file : "+config.getAbsolutePath());
			ObjectMapper mapper = new ObjectMapper();
			params = mapper.readValue(config, DB_Parameters.class);
		} catch (JsonParseException e) {
			System.out.println("Could not parse database configuration file !");
		} catch (JsonMappingException e) {
			System.out.println("Could not map database configuration file !");
		} catch (IOException e) {
			System.out.println("Could not read database configuration file !");
		} finally {
			if (params == null) params = new DB_Parameters();
		}
	}

	@Bean(name="dataSource")
	public DataSource dataSourceBean() {
		BasicDataSource dataSource = new BasicDataSource();
//		// MySql
//		dataSource.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
//		dataSource.setUrl("jdbc:mysql://localhost:3306/elite");
//		dataSource.setUsername("elite");
//		dataSource.setPassword("reitnorf");
//		// H2Database
//		dataSource.setDriverClassName(org.h2.Driver.class.getName());
//		dataSource.setUrl("jdbc:h2:./elite");
//		dataSource.setUsername("elite");
//		dataSource.setPassword("");
//		// SQLite
//		dataSource.setDriverClassName(org.sqlite.JDBC.class.getName());
//		dataSource.setUrl("jdbc:h2:elite.sqlite");
//		dataSource.setUsername("elite");
//		dataSource.setPassword("");
		// FILE CONFIGURATION
		dataSource.setDriverClassName(params.getDriverClassName());
		dataSource.setUrl(params.getUrl());
		dataSource.setUsername(params.getUsername());
		dataSource.setPassword(params.getPassword());
		System.out.println("DataSource: driverclassname="+dataSource.getDriverClassName());
		System.out.println("DataSource: url="+dataSource.getUrl());
		System.out.println("DataSource: username="+dataSource.getUsername());
		return dataSource;
	}

	Properties additionalProperties() {
		if (properties == null) {
			properties = new Properties();
//			properties.put("hibernate.current_session_context_class", "thread");
			properties.put("connection.pool_size", "1");
			properties.put("hibernate.hbm2ddl.auto", "update");
//			properties.put("hibernate.dialect", org.hibernate.dialect.H2Dialect.class.getName());
//			properties.put("hibernate.dialect", org.hibernate.dialect.MySQL5InnoDBDialect.class.getName());
//			properties.put("hibernate.dialect", org.hibernate.dialect.SQLiteDialect.class.getName());
			properties.put("hibernate.dialect", params.getDialect());
//			properties.put("hibernate.enable_lazy_load_no_trans", "true");
//			properties.put("hibernate.format_sql", "true");
			properties.put("show_sql", "true");
			System.out.println("Properties: hibernate.hbm2ddl.auto="+properties.getProperty("hibernate.hbm2ddl.auto"));
			System.out.println("Properties: hibernate.dialect="+properties.getProperty("hibernate.dialect"));
		}
		return properties;
	}

//	@Bean
//	public Map<String, Object> jpaProperties() {
//		System.out.println("Defining jpaProperties");
//		Map<String, Object> props = new HashMap<String, Object>();
//		props.put("hibernate.dialect", params.getDialect());
//		props.put("hibernate.ejb.naming_strategy", ImprovedNamingStrategy.class.getName());
//		props.put("hibernate.connection.charSet", "UTF-8");
//		props.put("hibernate.hbm2ddl.auto", "update");
//		return props;
//	}

	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		System.out.println("Defining entityManagerFactory bean");
		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setDataSource(this.dataSourceBean());
		entityManager.setPackagesToScan(new String[]{"fr.elite.jodev.dao.impl","fr.elite.jodev.services.impl"});
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter() {{}};
		vendorAdapter.setShowSql(true);
		entityManager.setJpaVendorAdapter(vendorAdapter);
		//		entityManager.setJpaPropertyMap(jpaProperties());
		entityManager.setJpaProperties(additionalProperties());
		return entityManager;
	}

	@Bean(name="sessionFactory")
	public LocalSessionFactoryBean sessionFactoryBean() {
		System.out.println("Defining sessionFactory bean");
		LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
		bean.setAnnotatedClasses(new Class[]{
				SolarSystem.class, Station.class,
				ShipOutfitCategory.class, ShipBuyable.class, ShipOutfitSlot.class,
				GoodsCategory.class, GoodsDesignation.class, Goods.class});		
		bean.setHibernateProperties(additionalProperties());
		bean.setDataSource(this.dataSourceBean());
		//		bean.setSchemaUpdate(true); // hibernate 3
		return bean;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		return new HibernateTransactionManager( sessionFactoryBean().getObject() );
	}
}
