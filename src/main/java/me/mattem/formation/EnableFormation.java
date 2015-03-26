package me.mattem.formation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import me.mattem.formation.configuration.FormationConfiguration;

import org.springframework.context.annotation.Import;

/**
 * Enables Formation in the Spring based project
 * 
 * @author Matt
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({FormationConfiguration.class})
public @interface EnableFormation {
	
	String[] basePackages() default {};
	
	Class<?>[] classes() default {};

}
