package me.mattem.formation.cache;

import java.util.ArrayList;
import java.util.List;

import me.mattem.formation.annotations.FormationInclude;
import me.mattem.formation.annotations.FormationInterface;
import me.mattem.formation.cache.objectdescriptors.AbstractObjectPropertyDescriptor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@FormationInclude
@JsonInclude(Include.NON_NULL)
public class FormationObjectHolder {
	private String objectName;
	public String getObjectName(){ return this.objectName; }
	public void setObjectName(String objectName){ this.objectName = objectName; }
	
	private List<ObjectPropertyHolder> propertyHolders  = new ArrayList<ObjectPropertyHolder>();
	public List<ObjectPropertyHolder> getPropertyHolders(){ return this.propertyHolders; }
	public void setPropertyHolders(List<ObjectPropertyHolder> propertyHolders){ this.propertyHolders = propertyHolders; }
	public void addPropertyHolder(ObjectPropertyHolder propertyHolder){ propertyHolders.add(propertyHolder); }
	
	@FormationInclude
	@JsonInclude(Include.NON_NULL)
	public static class ObjectPropertyHolder {
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
		@FormationInterface(typeCategory="ObjectPropertyDescriptors")
		public AbstractObjectPropertyDescriptor getObjectPropertyDescriptor(){ return this.objectPropertyDescriptor; }
	}
	
	@JsonInclude(Include.NON_NULL)
	@FormationInclude
	public static class ObjectPropertyTypeDescriptor {
		private List<String> generalTypes;
		public List<String> getGeneralTypes() { return generalTypes; }
		public void setGeneralTypes(List<String> generalTypes) { this.generalTypes = generalTypes; }
		public void addGeneralType(String generalType){ 
			if(this.generalTypes == null) generalTypes = new ArrayList<String>();
			generalTypes.add(generalType);
		}
		
		private List<ObjectPropertyTypeDescriptor> innerTypes;
		public List<ObjectPropertyTypeDescriptor> getInnerTypes() { return innerTypes; }
		public void setInnerTypes(List<ObjectPropertyTypeDescriptor> innerTypes) { this.innerTypes = innerTypes; }
		public void addInnerType(ObjectPropertyTypeDescriptor innerType){
			if(this.innerTypes == null) innerTypes = new ArrayList<ObjectPropertyTypeDescriptor>();
			innerTypes.add(innerType);
		}
		
		private boolean unknownType = false;
		public boolean isTypeUnknown(){ return unknownType; }
		public void setUnknownType(boolean unknownType) { this.unknownType = unknownType; }
	}
}
