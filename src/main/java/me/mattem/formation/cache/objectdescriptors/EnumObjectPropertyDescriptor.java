package me.mattem.formation.cache.objectdescriptors;

import java.util.ArrayList;
import java.util.List;

public class EnumObjectPropertyDescriptor extends AbstractObjectPropertyDescriptor {
	private List<String> values;
	public void setValues(List<String> values){ this.values = values; }
	public List<String> getValues(){ return this.values; }
	public void addValue(String value){ 
		if(this.values == null) this.values = new ArrayList<String>();
		this.values.add(value);
	}
}
