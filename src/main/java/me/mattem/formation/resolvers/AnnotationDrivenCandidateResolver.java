package me.mattem.formation.resolvers;

import org.springframework.beans.factory.config.BeanDefinition;

public class AnnotationDrivenCandidateResolver extends AbstractFormationCandidateResolver {
	
	@Override
	public boolean isCandidate(BeanDefinition beanDefinition) {
		Class<?> clazz = resolveClassName(beanDefinition.getBeanClassName());
		return this.hasIncludeAnnotation(clazz);
	}

}
