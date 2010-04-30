package it.nexus.enterprise.annotations;

import it.nexus.core.BasePlugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.TYPE })
public @interface AccessGroup {
	/**
	 * Dec 24, 2009 AccessGroup.java dcriori
	 */
	String name();

	Class<?> pluginClass() default BasePlugin.class;
}
