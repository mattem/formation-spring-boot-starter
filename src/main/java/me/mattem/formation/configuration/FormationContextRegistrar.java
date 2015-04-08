package me.mattem.formation.configuration;

import java.util.Map;

import javax.annotation.PostConstruct;

import me.mattem.formation.EnableFormation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

@Component
public class FormationContextRegistrar {
	
	@Autowired private ApplicationContext applicationContext;
	
	private FormationContext formationContext;
	public FormationContext getContext(){ return formationContext; }
	
	@PostConstruct
	private void init(){
		Map<String, Object> beans = applicationContext.getBeansWithAnnotation(EnableFormation.class);
		beans.forEach((beanName, bean)->{
			makeFormationContext(beanName, bean);
		});
	}
	
	private void makeFormationContext(String beanName, Object bean) throws IllegalStateException {
		if(formationContext != null){
			throw new IllegalStateException("More than one @EnableFormation annotation found in application context");
		}
		
		EnableFormation enableFormation = AnnotationUtils.findAnnotation(bean.getClass(), EnableFormation.class);
		if(enableFormation != null){
			formationContext = new FormationContext(enableFormation, bean.getClass());
			//applicationContext.getAutowireCapableBeanFactory().applyBeanPropertyValues(formationContext, "formationContext");
		}
	}

}
