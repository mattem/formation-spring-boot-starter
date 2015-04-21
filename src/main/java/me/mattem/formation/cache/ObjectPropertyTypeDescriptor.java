package me.mattem.formation.cache;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ObjectPropertyTypeDescriptor {
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