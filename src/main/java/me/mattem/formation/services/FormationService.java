package me.mattem.formation.services;

import java.util.List;
import java.util.Set;

import me.mattem.formation.cache.FormationObjectCache;
import me.mattem.formation.cache.FormationObjectHolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormationService {

	@Autowired private FormationObjectCache cache;
	
	public FormationObjectHolder getObject(String name){
		return cache.getObjectHolder(name);
	}
	
	public Set<String> getKnownObjectNames(){
		return cache.read().keySet();
	}
	
	public List<FormationObjectHolder> getObjectsForCategory(String category){
		return cache.getCategory(category);
	}

	public void rebuild() {
		cache.clear();
		
	}
}
