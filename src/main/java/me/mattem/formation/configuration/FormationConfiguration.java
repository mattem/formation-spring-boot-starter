package me.mattem.formation.configuration;

import me.mattem.formation.SimpleCorsFilter;
import me.mattem.formation.cache.FormationObjectCache;
import me.mattem.formation.processors.DefaultFormationObjectProcessor;
import me.mattem.formation.processors.FormationObjectProcessor;
import me.mattem.formation.resolvers.AnnotationDrivenCandidateResolver;
import me.mattem.formation.resolvers.FormationCandidateResolver;
import me.mattem.formation.scanners.FormationObjectScanner;
import me.mattem.formation.scanners.FormationObjectScannerResult;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages="com.jarvis.formation")
public class FormationConfiguration {
	
	@Bean
	public FormationContext formationContext(FormationContextRegistrar registrar){
		return registrar.getContext();
	}

	@Bean
	@ConditionalOnMissingBean
	public FormationCandidateResolver candidateResolver(){
		return new AnnotationDrivenCandidateResolver();
	}
	
	@Bean 
	public FormationObjectScannerResult scannerResult(FormationObjectScanner scanner){
		return scanner.scan();
	}
	
	@Bean
	@ConditionalOnMissingBean
	public FormationObjectProcessor objectProcessor(FormationObjectScannerResult scannerResult, FormationObjectCache objectCache){
		return new DefaultFormationObjectProcessor(scannerResult, objectCache);
	}
	
	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnWebApplication
	public SimpleCorsFilter corsFilter(){
		return new SimpleCorsFilter();
	}

}
