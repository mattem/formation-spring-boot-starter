package me.mattem.formation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import me.mattem.formation.annotations.FormationInclude;
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
	
	/**
	 * A list of base packages to scan, if none are supplied, then the package of the class the 
	 * annotation is applied to will be used. 
	 */
	String[] basePackages() default {};
	
	/**
	 * A list of classes that are to be processed by Formation. 
	 * These classes are in addition to classes found via package scanning. 
	 */
	Class<?>[] classes() default {};
	
	/**
	 * <p>By setting uniqueClassNames to true, Formation will use a pretty version of <code>class.getSimpleName()</code> 
	 * instead of <code>class.getName()</code></p>
	 * 
	 * <p>By setting this option to true, you can bypass the need to set unique names within the {@link FormationInclude}
	 * annotation, but class names within your application must be unique (An {@link IllegalArgumentException} is thrown during 
	 * startup if a name space clash is detected</p> 
	 * @return
	 */
	boolean uniqueClassNames() default false;

}
