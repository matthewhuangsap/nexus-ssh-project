package it.nexus.enterprise.bill.service;

import it.nexus.core.service.BaseService;
import it.nexus.enterprise.bill.dao.BillDAO;
import it.nexus.enterprise.bill.model.Bill;

@SuppressWarnings("unchecked")
public class BillService<T extends Bill,K extends BillDAO> extends BaseService<Bill,BillDAO> {
	public BillService(){}
	public BillService(BillDAO<Bill,Long> dao) {
		super(dao);
	}


}
