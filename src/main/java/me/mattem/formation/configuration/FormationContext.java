package me.mattem.formation.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.mattem.formation.EnableFormation;

import org.springframework.util.Assert;

public final class FormationContext {

	private final List<String> basePackages;
	public List<String> getBasePackages() { return basePackages; }
	
	private final List<Class<?>> classes;
	public List<Class<?>> getClasses() { return classes; }
	
	public FormationContext(List<String> basePackages, List<Class<?>> classes){
		this.basePackages = basePackages;
		this.classes = classes;
	}
	
	public FormationContext(EnableFormation enableFormation, Class<?> containingClass){
		Assert.notNull(enableFormation, "EnableFormation annotation must not be null");
		Assert.notNull(containingClass, "Containing class must not be null");
		
		this.basePackages = new ArrayList<String>(Arrays.asList(enableFormation.basePackages()));
		this.classes = new ArrayList<Class<?>>(Arrays.asList(enableFormation.classes()));
		
		if(basePackages.isEmpty()){
			basePackages.add(containingClass.getPackage().getName());
		}
		
	}

}
