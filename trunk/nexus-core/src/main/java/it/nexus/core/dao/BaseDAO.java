package it.nexus.core.dao;

import it.nexus.core.models.Base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BaseDAO<T extends Base> extends HibernateDaoSupport implements
		IBaseDAO<T> {

	@Resource(name = "sessionFactory")
	// 为父类HibernateDaoSupport注入sessionFactory的值
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	protected Class<T> getGenericClass() {
		Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		return entityClass;
	}

	public void delete(T entity) {
		System.out.println("base DAO delete method begin");
		getHibernateTemplate().delete(entity);
		System.out.println("base DAO delete method end");
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		System.out.println("get all :" + getGenericClass().getSimpleName());
		return (List<T>) getHibernateTemplate().find(
				"from " + getGenericClass().getSimpleName());
	}

	@SuppressWarnings("unchecked")
	public T load(Serializable id) {
		return (T) getHibernateTemplate().get(getGenericClass(), id);
	}

	public void save(T entity) {
		getHibernateTemplate().save(entity);
	}

	public void saveOrUpdate(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	public void merge(T entity) {
		getHibernateTemplate().merge(entity);
	}

	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Class<?>> executeHql(final String hql,final Object... params ) {
		List<Class<?>> list = (List<Class<?>>) getHibernateTemplate().execute(
			new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					Query query = session.createQuery(hql);
					int i=0;
					for (Object param : params) {
						if(param.getClass()== String.class)
							query.setString(i, (String)param);
						else if(param.getClass()==Long.class)
							query.setLong(i, (Long)param);
						else {
							System.out.println("没有该数据类型");
						}
						i++;
					}
					List<Class<?>> list = query.list();
					return list;
				}
			});
		return list;
	}

}
