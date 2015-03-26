package me.mattem.formation.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import me.mattem.formation.configuration.FormationDefaults;

/**
 * The {@link FormationMap} annotation allows overriding or supplementing information that has already been gleamed via inspecting the Formation Object.
 * @author matt
 *
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FormationMap {
	
	/**
	 * The label to use on the form for the key. Defaults to {@value FormationDefaults.MAP_KEY_LABEL}
	 */
	String keyLabel() default FormationDefaults.MAP_KEY_LABEL;
	
	/**
	 * The label to use on the form for the value. Defaults to {@value FormationDefaults.MAP_VALUE_LABEL}
	 */
	String valueLabel() default FormationDefaults.MAP_VALUE_LABEL;
}
