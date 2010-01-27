package it.nexus.core.annotation;

import it.nexus.core.models.enums.BaseOperation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Access {
	/**
	 * Dec 23, 2009 Access.java Administrator
	 */
	Class<?> enumClass() default BaseOperation.class;
	String value();
}
