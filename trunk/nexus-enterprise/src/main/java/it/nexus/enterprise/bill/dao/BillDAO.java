package it.nexus.enterprise.bill.dao;

import it.nexus.core.dao.EntityDAO;
import it.nexus.enterprise.bill.model.Bill;

import java.io.Serializable;

public class BillDAO<T extends Bill,PK extends Serializable> extends EntityDAO<T,PK> implements
		IBillDAO<T> {
	
}
