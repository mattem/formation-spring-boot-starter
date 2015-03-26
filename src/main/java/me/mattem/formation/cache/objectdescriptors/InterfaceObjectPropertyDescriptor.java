package me.mattem.formation.cache.objectdescriptors;

import me.mattem.formation.annotations.FormationInclude;

@FormationInclude(typeCategories={"ObjectPropertyDescriptors"})
public class InterfaceObjectPropertyDescriptor extends AbstractObjectPropertyDescriptor {
	private String typeCategory;
	public void setTypeCategory(String typeCategory){ this.typeCategory = typeCategory; }
	public String getTypeCategory(){ return this.typeCategory; }

}
