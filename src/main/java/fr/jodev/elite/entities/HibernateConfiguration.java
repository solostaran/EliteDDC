package fr.jodev.elite.entities;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.dialect.MySQL5Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {

//	@Value("#{dataSource}")
//	private DataSource dataSource;
	
	@Bean(name="dataSource")
	public DataSource dataSourceBean() {
		BasicDataSource dataSource = new BasicDataSource();
	      dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	      dataSource.setUrl("jdbc:mysql://localhost:3306/elite");
	      dataSource.setUsername( "elite" );
	      dataSource.setPassword( "reitnorf" );
	      return dataSource;
	}

	@Bean(name="sessionFactory")
	public LocalSessionFactoryBean sessionFactoryBean() {
		Properties props = new Properties();
//		props.put("hibernate.connection.driver_class", com.mysql.jdbc.Driver.class.getName());
//		props.put("hibernate.connection.url", "jdbc:mysql://localhost/elite");
//		props.put("hibernate.connection.username", "elite");
//		props.put("hibernate.connection.password", "reitnorf");
//		props.put("hibernate.default_schema", "elite");
		
//		props.put("hibernate.current_session_context_class", "thread");
		props.put("connection.pool_size", "1");
//		props.put("cache.provider_class", "org.hibernate.cache.NoCacheProvider");
		props.put("hibernate.dialect", MySQL5Dialect.class.getName());
		props.put("show_sql", "true");
		props.put("hibernate.format_sql", "true");
		props.put("hibernate.hbm2ddl.auto", "update");

		LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
		bean.setAnnotatedClasses(new Class[]{SolarSystem.class, Station.class});		
		bean.setHibernateProperties(props);
		bean.setDataSource(this.dataSourceBean());
//		bean.setSchemaUpdate(true); // hibernate 3
		return bean;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		return new HibernateTransactionManager( sessionFactoryBean().getObject() );
	}

}
