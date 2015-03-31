package me.mattem.formation.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class FormationObjectCache {
	private Map<String, FormationObjectHolder> namedCache = new HashMap<String, FormationObjectHolder>();
	private Map<String, FormationObjectHolder> classedCache = new HashMap<String, FormationObjectHolder>();
	private Map<String, List<FormationObjectHolder>> categoryForType = new HashMap<String, List<FormationObjectHolder>>();
	
	public Map<String, FormationObjectHolder> read(){
		return Collections.unmodifiableMap(namedCache);
	}
	
	public void putObjectHolder(FormationObjectHolder objectHolder){
		namedCache.put(objectHolder.getObjectName(), objectHolder);
		classedCache.put(objectHolder.getClassName(), objectHolder);
	}
	
	public FormationObjectHolder getObjectHolder(String objectName){
		FormationObjectHolder objectHolder = namedCache.get(objectName);
		if(objectHolder == null)
			objectHolder = classedCache.get(objectName);
		return objectHolder;
	}
	
	public List<FormationObjectHolder> getCategory(String typeCategory){
		return categoryForType.get(typeCategory);
	}
	
	public void putCategory(String typeCategory, FormationObjectHolder objectHolder){
		if(getCategory(typeCategory) == null){
			categoryForType.put(typeCategory, new ArrayList<FormationObjectHolder>());
		}
		getCategory(typeCategory).add(objectHolder);
	}
	
	public void clear(){
		namedCache.clear();
		classedCache.clear();
		categoryForType.clear();
	}
}
