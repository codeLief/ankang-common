package com.aiggo.common.util.smart.validate.match.rule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxValueValidate {

	String message () default "";
	
	String name () default "";
	
	String value ();

}
