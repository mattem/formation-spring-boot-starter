package me.mattem.formation.scanners;

import java.util.Set;

import me.mattem.formation.annotations.FormationExclude;
import me.mattem.formation.configuration.FormationContext;
import me.mattem.formation.resolvers.FormationCandidateResolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

@Component
public class DefaultFormationObjectScanner implements FormationObjectScanner {
	private FormationCandidateResolver candidateResolver;
	private FormationContext formationContext;
	
	@Autowired
	public DefaultFormationObjectScanner(FormationContext formationContext, FormationCandidateResolver candidateResolver) {
		this.formationContext = formationContext;
		this.candidateResolver = candidateResolver;
	}
	
	@Override
	public FormationObjectScannerResult scan() {
		FormationObjectScannerResult result = new FormationObjectScannerResult();
		
		for(String basePackage : formationContext.getBasePackages()){
			Set<BeanDefinition> candidates = this.findServiceCandidates(basePackage);
			
			for(BeanDefinition candidate : candidates){
				result.incrementCandidatesScanned();
				Class<?> candidateClass = resolveClassName(candidate.getBeanClassName());
				if(candidateResolver.isCandidate(candidate)){
					result.addClass(candidateClass);
				}else{
					result.incrementCandidatesRejected();
				}
			}
		}
		
		return result;
	}
	
	private Set<BeanDefinition> findServiceCandidates(String basePackage){
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
		scanner.addIncludeFilter(new AssignableTypeFilter(Object.class));
		scanner.addExcludeFilter(new AnnotationTypeFilter(FormationExclude.class, false, false));
		return scanner.findCandidateComponents(basePackage);
	}
	
	private Class<?> resolveClassName(String className){
		return ClassUtils.resolveClassName(className, ClassUtils.getDefaultClassLoader());
	}

}