package me.mattem.formation.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FormationInclude {
	
	/**
	 * The name for this Object to be used by Formation. 
	 * If left blank, then the Class name is used 
	 */
	String name() default "";
	
	/**
	 * If inherit is set to {@code true}, then the class hierarchy is searched for suitable methods 
	 * (only ignoring those on {@code Object.class}). 
	 * 
	 * When {@code false}, only the annotated object is considered. 
	 */
	boolean inherit() default true;
	
	/**
	 * A list of type categories that are used when a Formation Type annotation is on a abstract or interface setter.
	 */
	String[] typeCategories() default {};

}
