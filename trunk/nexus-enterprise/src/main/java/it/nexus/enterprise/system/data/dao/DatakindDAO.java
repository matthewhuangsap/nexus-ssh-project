package it.nexus.enterprise.system.data.dao;

import it.nexus.core.dao.BaseDAO;
import it.nexus.core.models.Base;
import it.nexus.core.tools.xml.XmlUtils;

import java.util.List;

import org.dom4j.Document;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository
public class DatakindDAO extends BaseDAO<Base,String> {
	
	public List<?> getDataKind(Document document,String dk){
		String query = XmlUtils.getNamedQueryValue(document, dk);
		Query queryList = getSession().getNamedQuery(query);
		List<?> list = queryList.list();
		return list;
	}
}
