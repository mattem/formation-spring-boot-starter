package me.mattem.formation.cache;

import java.util.HashSet;
import java.util.Set;

import me.mattem.formation.annotations.FormationFieldType;
import me.mattem.formation.cache.objectdescriptors.AbstractObjectPropertyDescriptor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ObjectPropertyDescriptor {
	private String properyName;
	public String getProperyName() { return properyName; }
	public void setProperyName(String properyName) { this.properyName = properyName; }
	
	private ObjectPropertyTypeDescriptor propertyTypeDescriptor;
	public ObjectPropertyTypeDescriptor getPropertyTypeDescriptor() { return propertyTypeDescriptor; }
	public void setPropertyTypeDescriptor(ObjectPropertyTypeDescriptor propertyTypeDescriptor) { this.propertyTypeDescriptor = propertyTypeDescriptor; }
	
	private String propertyGeneralType;
	public String getPropertyGeneralType() { return propertyGeneralType; }
	public void setPropertyGeneralType(String propertyGeneralType) { this.propertyGeneralType = propertyGeneralType; }
	
	private AbstractObjectPropertyDescriptor objectPropertyDescriptor;
	public void setObjectPropertyDescriptor(AbstractObjectPropertyDescriptor objectPropertyDescriptor){ this.objectPropertyDescriptor = objectPropertyDescriptor; }
	public AbstractObjectPropertyDescriptor getObjectPropertyDescriptor(){ return this.objectPropertyDescriptor; }

	private FormationFieldType fieldType = FormationFieldType.AS_CLASS;
	public FormationFieldType getFieldType() { return fieldType; }
	public void setFieldType(FormationFieldType fieldType) { this.fieldType = fieldType; }

	private String fieldPattern = "";
	public String getFieldPattern() { return fieldPattern; }
	public void setFieldPattern(String fieldPattern) { this.fieldPattern = fieldPattern; }

	private boolean isRequired = false;
	public boolean isRequired() { return isRequired; }
	public void setRequired(boolean isRequired) { this.isRequired = isRequired; }

	private Set<String> views = new HashSet<String>();
	public Set<String> getViews() { return views; }
	public void setViews(Set<String> views) { this.views = views; }
}