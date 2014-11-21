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
		
//		props.put("hibernate.current_session_context_class", "thread");
		props.put("connection.pool_size", "1");
//		props.put("cache.provider_class", "org.hibernate.cache.NoCacheProvider");
		props.put("hibernate.dialect", MySQL5Dialect.class.getName());
//		props.put("hibernate.enable_lazy_load_no_trans", "true");
		props.put("show_sql", "true");
		props.put("hibernate.format_sql", "true");
		props.put("hibernate.hbm2ddl.auto", "update");

		LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
		bean.setAnnotatedClasses(new Class[]{
				SolarSystem.class, Station.class,
				ShipOutfitCategory.class, ShipBuyable.class, ShipOutfitSlot.class});		
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
