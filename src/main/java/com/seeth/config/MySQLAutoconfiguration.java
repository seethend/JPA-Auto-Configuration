package com.seeth.config;

import java.util.Arrays;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.boot.autoconfigure.condition.ConditionMessage.Style;
import org.springframework.context.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.util.ClassUtils;

@Configuration
@ConditionalOnClass(DataSource.class)
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@PropertySource("classpath:mysql.properties")
public class MySQLAutoconfiguration {

	 @Autowired
	 private Environment env;
	 
	@Bean
	@ConditionalOnBean(name = "dataSource")
	@ConditionalOnMissingClass
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em
		= new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan("com");
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		if (additionalProperties() != null) {
			em.setJpaProperties(additionalProperties());
		}
		return em;
	}

	@Bean
	@ConditionalOnMissingBean(type = "JpaTransactionManager")
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

	@Bean
	@ConditionalOnProperty(
			name = "usemysql", 
			havingValue = "local")
	@ConditionalOnMissingBean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		System.out.println("\nInside datasource " + env.getProperty("usemysql"));
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/spring_db");
		dataSource.setUsername("root");
		dataSource.setPassword("seeth");

		return dataSource;
	}

	@ConditionalOnResource(
			resources = "classpath:mysql.properties")
	@Conditional(HibernateCondition.class)
	Properties additionalProperties() {
		System.out.println("\nInside Additional Properties \n ");
		Properties hibernateProperties = new Properties();

		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		return hibernateProperties;
	}
	
	static class HibernateCondition extends SpringBootCondition {

		private static String[] CLASS_NAMES = { "org.hibernate.ejb.HibernateEntityManager", "org.hibernate.jpa.HibernateEntityManager" };

		@Override
		public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {

			ConditionMessage.Builder message = ConditionMessage.forCondition("Hibernate");
			return Arrays.stream(CLASS_NAMES)
					.filter(className -> ClassUtils.isPresent(className, context.getClassLoader()))
					.map(className -> ConditionOutcome
							.match(message.found("class")
									.items(Style.NORMAL, className)))
					.findAny()
					.orElseGet(() -> ConditionOutcome
							.noMatch(message.didNotFind("class", "classes")
									.items(Style.NORMAL, Arrays.asList(CLASS_NAMES))));}
	}
}
