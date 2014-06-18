package com.mitake.hibernate;

import org.hibernate.Session;

public interface IBaseHibernateDAO {
	public Session getCurrentSession();
}
