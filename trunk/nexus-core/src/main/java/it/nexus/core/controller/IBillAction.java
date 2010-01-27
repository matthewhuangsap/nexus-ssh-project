package it.nexus.core.controller;

import it.nexus.core.models.Bill;

public interface IBillAction<T extends Bill> extends IBaseAction<T> {
	String check () throws Exception;
	String uncheck()throws Exception;
	String finish() throws Exception;
	String invalid() throws Exception;
}
