package me.mattem.formation.processors;

import me.mattem.formation.cache.FormationObjectHolder;

public interface FormationObjectProcessor {
	
	public boolean canProcessClass(Class<?> clazz);
	
	public FormationObjectHolder processClass(Class<?> clazz);
	
	public boolean onStartProcessingResults();
	
	public boolean onDoneProcessingResults();
	
}
