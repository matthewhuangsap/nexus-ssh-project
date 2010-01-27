package it.nexus.core.controller;

import it.nexus.core.models.Entity;

/** 
 * Jan 18, 2010
 * dcriori
*/
public interface IEntityAction<T extends Entity> extends IBaseAction<T> {
	String lock()throws Exception;
	String unlock()throws Exception;
	String previous()throws Exception;
	String next() throws Exception;
}
