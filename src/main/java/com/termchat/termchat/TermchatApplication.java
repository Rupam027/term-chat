package com.termchat.termchat;

import java.util.* ; 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.* ; 
import org.springframework.context.annotation.* ; 
import org.springframework.data.jpa.repository.config.* ; 
import org.springframework.orm.jpa.* ;
import org.springframework.orm.jpa.vendor.* ; 
import org.springframework.jdbc.datasource.* ; 





@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.termchat.termchat.models")
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


	@Bean(name="entityManageFactory")
	public  LocalContainerEntityManagerFactoryBean entitymanagerfactory(){

		 LocalContainerEntityManagerFactoryBean entitymanager = new  LocalContainerEntityManagerFactoryBean();
		 entitymanager.setDataSource(datasource());
		 entitymanager.setPackagesToScan("com.termchat.termchat.models");

		 JpaVendorAdapter vendor = new HibernateJpaVendorAdapter();
		 entitymanager.setJpaVendorAdapter(vendor);
      	 

		 return entitymanager ; 



	}		
	




	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(TermchatApplication.class) ; 
		app.run(args);


	}

}	
