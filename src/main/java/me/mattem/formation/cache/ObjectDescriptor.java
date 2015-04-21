package me.mattem.formation.cache;

import java.util.HashSet;
import java.util.Set;

public class ObjectDescriptor {
	private Set<String> availableViews;

	public Set<String> getAvailableViews() { return availableViews; }
	public void setAvailableViews(Set<String> availableViews) { this.availableViews = availableViews; }
	public void addAllViews(Set<String> views){ 
		if(this.availableViews == null){
			this.availableViews = new HashSet<String>();
		}
		this.availableViews.addAll(views); 
	}

}
