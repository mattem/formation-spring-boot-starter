package me.mattem.formation.cache;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class FormationObjectHolder {
	private String objectName;
	public String getObjectName(){ return this.objectName; }
	public void setObjectName(String objectName){ this.objectName = objectName; }
	
	private String className;
	public String getClassName() { return this.className; }
	public void setClassName(String className){ this.className = className; }
	
	private List<ObjectPropertyDescriptor> propertyHolders  = new ArrayList<ObjectPropertyDescriptor>();
	public List<ObjectPropertyDescriptor> getPropertyHolders(){ return this.propertyHolders; }
	public void setPropertyHolders(List<ObjectPropertyDescriptor> propertyHolders){ this.propertyHolders = propertyHolders; }
	public void addPropertyHolder(ObjectPropertyDescriptor propertyHolder){ propertyHolders.add(propertyHolder); }

	private ObjectDescriptor objectDescriptor;
	public ObjectDescriptor getObjectDescriptor() { return objectDescriptor; }
	public void setObjectDescriptor(ObjectDescriptor objectDescriptor) { this.objectDescriptor = objectDescriptor; }
	
}
