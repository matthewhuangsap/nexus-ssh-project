package it.nexus.enterprise.bill.controller;

import it.nexus.core.controller.IEntityAction;
import it.nexus.enterprise.bill.model.Bill;

public interface IBillAction<T extends Bill> extends IEntityAction<T> {
	String check () throws Exception;
	String uncheck()throws Exception;
	String finish() throws Exception;
	String invalid() throws Exception;
}
