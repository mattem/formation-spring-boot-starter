package me.mattem.formation.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * General annotation for adding validation rules to an annotated field, or for changing the behavior of an existing property.
 * @author matt
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface FormationField {
	
	/**
	 * Sets the required property of this field to true. 
	 * By default, all Formation fields are not required, unless they are parameters in a FormationConstructor
	 */
	boolean required() default false;
	
	/**
	 * Sets a regular expression pattern to use on this field
	 */
	String pattern() default "";
	
	/**
	 * Allows setting the input type on the Formation form field. 
	 * Note however, that not all of these are supported in all browsers.
	 */
	FormationFieldType fieldType() default FormationFieldType.AS_CLASS;
	
	/**
	 * Overrides the discovered class with the one provided.
	 */
	Class<?> overrideAs() default void.class;
	
	/**
	 * Allows assigning a 'view' over this object that not all fields might be included in. 
	 * For example, a view might only contain 3 required fields while the whole object could contain any number of fields. 
	 * When a view is passed to ngFormation, then only the fields in that view are built and rendered in the UI.
	 * By default, all fields are included.
	 */
	String[] views() default {};
	
}
