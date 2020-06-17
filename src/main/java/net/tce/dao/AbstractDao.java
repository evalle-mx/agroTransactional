package net.tce.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao {

		
	@Autowired
    private SessionFactory sessionFactory;
 
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
