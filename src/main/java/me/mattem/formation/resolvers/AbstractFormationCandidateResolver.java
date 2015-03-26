package me.mattem.formation.resolvers;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import me.mattem.formation.annotations.FormationExclude;
import me.mattem.formation.annotations.FormationInclude;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;

abstract class AbstractFormationCandidateResolver implements FormationCandidateResolver {

	@SuppressWarnings("serial")
	private static final List<Class<? extends Annotation>> stereotypes = new ArrayList<Class<? extends Annotation>>() {
		{
			this.add(Component.class);
			this.add(Controller.class);
			this.add(Service.class);
			this.add(Repository.class);
			this.add(Configuration.class);
		}
	};

	protected Class<?> resolveClassName(String className){
		return ClassUtils.resolveClassName(className, ClassUtils.getDefaultClassLoader());
	}
	
	protected boolean hasStereotypeAnnotation(Class<?> clazz){
		return AnnotationUtils.findAnnotationDeclaringClassForTypes(stereotypes, clazz) != null ? true : false;
	}
	
	protected boolean hasIncludeAnnotation(Class<?> clazz){
		return AnnotationUtils.findAnnotation(clazz, FormationInclude.class) != null ? true : false;
	}
	
	protected boolean hasExcludeAnnotation(Class<?> clazz){
		return AnnotationUtils.findAnnotation(clazz, FormationExclude.class) != null ? true : false;
	}

}
