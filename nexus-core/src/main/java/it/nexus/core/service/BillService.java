package it.nexus.core.service;

import it.nexus.core.dao.BillDAO;
import it.nexus.core.models.Bill;

@SuppressWarnings("unchecked")
public class BillService<T extends Bill,K extends BillDAO> extends BaseService<Bill,BillDAO> {
	public BillService(){}
	public BillService(BillDAO<Bill> dao) {
		super(dao);
	}


}
