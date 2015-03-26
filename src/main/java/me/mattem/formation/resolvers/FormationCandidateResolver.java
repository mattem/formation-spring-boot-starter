package me.mattem.formation.resolvers;

import org.springframework.beans.factory.config.BeanDefinition;

public interface FormationCandidateResolver {
	
	public boolean isCandidate(BeanDefinition  beanDefinition);
}
