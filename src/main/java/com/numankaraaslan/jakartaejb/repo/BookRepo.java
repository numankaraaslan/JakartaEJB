package com.numankaraaslan.jakartaejb.repo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.criteria.JpaCriteriaQuery;

import com.numankaraaslan.jakartaejb.config.BeanFactory;
import com.numankaraaslan.jakartaejb.model.Book;

@jakarta.ejb.Singleton
//This bean will only be initialized once in the applications lifetime
//NOT @jakarta.inject.Singleton
public class BookRepo
{
	private BeanFactory factory;

	// @PersistenceContext didn't work here
	// Because i was unable to create sessionfactory bean
	// public SessionFactory sessionFactory;

	// @Inject is actually new and improved @jakarta.ejb.EJB
	@jakarta.inject.Inject
	public void setFactory(BeanFactory factory)
	{
		this.factory = factory;
	}

	public List<Book> getBooks()
	{
		// Criteria API :@
		Session session = this.factory.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		JpaCriteriaQuery<Book> query = session.getCriteriaBuilder().createQuery(Book.class);
		query.from(Book.class);
		List<Book> list = session.createQuery(query).getResultList();
		session.getTransaction().commit();
		return list;
	}

	public void save(Book book)
	{
		Session session = this.factory.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		session.save(book);
		session.getTransaction().commit();
	}
}
