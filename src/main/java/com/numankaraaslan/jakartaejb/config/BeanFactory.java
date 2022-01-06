package com.numankaraaslan.jakartaejb.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jakarta.annotation.PostConstruct;

@jakarta.ejb.Singleton
// This bean will only be initialized once in the applications lifetime
public class BeanFactory
{
	// Instead of using @jakarta.persistence.PersistenceContext, we are using session factory of hibernate
	private SessionFactory sessionFactory;

	// Initialize the session factory only once
	@PostConstruct
	public void initialize()
	{
		// this "configure("hibernate.cfg.xml")" automatically reads from WEB-INF folder
		// remember to include the "resources" folder in the classpath of the application
		// this way, the cfg file will be placed directly in WEB-INF folder
		sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		// you can also hardcode properties like below
		// sessionFactory = new Configuration().setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5433/EJB");
	}

	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
}
