package com.XEasy.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface URIMapping
{
	public String uri();
	public Method Method() default Method.DEFAULT;
	enum Method{
		DEFAULT,POST,GET,PUT,DELETE,UPDATE
	}
}
