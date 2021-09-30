package com.termchat.termchat;

import java.util.* ; 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.beans.factory.annotation.* ; 
import org.springframework.context.annotation.* ; 
import org.springframework.data.jpa.repository.config.* ; 
import org.springframework.orm.jpa.* ;
import org.springframework.orm.jpa.vendor.* ; 
import org.springframework.jdbc.datasource.* ;
import javax.persistence.*; 
import org.springframework.transaction.PlatformTransactionManager;
import java.util.*; 






@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.termchat.termchat.models")
@EntityScan(basePackages = "com.termchat.termchat.models")
public class TermchatApplication {

	
	
	@Value("${spring.datasource.driver-class-name}")
	private String DriverClassName ;  
	
	@Value("${spring.datasource.url}")
	private String database_url ;
	
	@Value("${spring.datasource.username}")
	private String database_username ; 

    @Value("${spring.datasource.password}")
	private String database_password ; 

	
	@Bean(name="datasource")
	public DriverManagerDataSource datasource(){

		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setDriverClassName(DriverClassName) ; 
		datasource.setUrl(database_url);
		datasource.setUsername(database_username);
		datasource.setPassword(database_password);

		return datasource ; 


	}


	@Bean(name="entityManagerFactory")
	public  LocalContainerEntityManagerFactoryBean entitymanagerfactory(){

		 LocalContainerEntityManagerFactoryBean entitymanager = new  LocalContainerEntityManagerFactoryBean();
		 entitymanager.setDataSource(datasource());
		 entitymanager.setPackagesToScan("com.termchat.termchat.models");
		 entitymanager.setPersistenceUnitName("org.hibernate.jpa.HibernatePersistenceProvider");
		 JpaVendorAdapter vendor = new HibernateJpaVendorAdapter();
		 entitymanager.setJpaVendorAdapter(vendor);
		 
      	 

		 return entitymanager ; 



	}		

	/*

	@Bean(name = "transactionmanager") 
	public PlatformTransactionManager transactionmanager(EntityManagerFactory entitymanagerfactory)
	{

		JpaTransactionManager tx = new JpaTransactionManager();
		tx.setEntityManagerFactory(entitymanagerfactory);

		return tx ; 


	}
	
	*/



	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(TermchatApplication.class) ; 
		app.run(args);


	}

}	
