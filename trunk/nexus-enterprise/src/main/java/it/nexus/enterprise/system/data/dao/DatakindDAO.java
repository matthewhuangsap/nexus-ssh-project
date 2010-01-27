package it.nexus.enterprise.system.data.dao;

import it.nexus.core.dao.BaseDAO;
import it.nexus.core.tools.xml.XmlUtils;

import java.util.List;

import org.dom4j.Document;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository("dkDAO")
public class DatakindDAO extends BaseDAO {
	
	public List<?> getDataKind(Document document,String dk){
		String query = XmlUtils.getNamedQueryValue(document, dk);
//		List<?> list = getHibernateTemplate().findByNamedQuery(query);
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query queryList = session.getNamedQuery(query);
		List<?> list = queryList.list();
		return list;
	}
}
