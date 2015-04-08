package me.mattem.formation.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.mattem.formation.EnableFormation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

public final class FormationContext {

	private final List<String> basePackages;
	public List<String> getBasePackages() { return basePackages; }
	
	private final List<Class<?>> classes;
	public List<Class<?>> getClasses() { return classes; }
	
	private final boolean guaranteedUniqueClassNames;
	public boolean isGuaranteedUniqueClassNames(){ return guaranteedUniqueClassNames; }
	
	private String isSomething;
	public String isSomething(){ return this.isSomething; }
	public void setSomething(String isSomething){ this.isSomething = isSomething; }
	
	public FormationContext(EnableFormation enableFormation, Class<?> containingClass){
		Assert.notNull(enableFormation, "EnableFormation annotation must not be null");
		Assert.notNull(containingClass, "Containing class must not be null");
		
		this.basePackages = new ArrayList<String>(Arrays.asList(enableFormation.basePackages()));
		this.classes = new ArrayList<Class<?>>(Arrays.asList(enableFormation.classes()));
		
		if(basePackages.isEmpty()){
			basePackages.add(containingClass.getPackage().getName());
		}
		
		this.guaranteedUniqueClassNames = enableFormation.uniqueClassNames();
	}

}
