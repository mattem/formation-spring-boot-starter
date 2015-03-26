package me.mattem.formation.resolvers;

import org.springframework.beans.factory.config.BeanDefinition;

public class PojoMatchingCandidateResolver extends AbstractFormationCandidateResolver {

	@Override
	public boolean isCandidate(BeanDefinition beanDefinition) {
		Class<?> clazz = resolveClassName(beanDefinition.getBeanClassName());
		return !this.hasStereotypeAnnotation(clazz);
	}

}
