package me.mattem.formation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.mattem.formation.cache.FormationObjectHolder.ObjectPropertyHolder;
import me.mattem.formation.cache.objectdescriptors.EnumObjectPropertyDescriptor;

public final class FormationTestHelper {

	private FormationTestHelper(){};
	
	public static ObjectPropertyHolder getProperty(List<ObjectPropertyHolder> properties, String propertyName){
		assertNotNull(propertyName);
		assertNotNull(properties);

		for(ObjectPropertyHolder prop : properties){
			if(prop.getProperyName().equals(propertyName)){
				return prop;
			}
		}
		return null;
	}
	
	public static boolean hasProperty(List<ObjectPropertyHolder> properties, String propertyName){
		if(getProperty(properties, propertyName) == null) return false;
		return true;
	}
	
	public static boolean hasPropertyOfType(List<ObjectPropertyHolder> properties, String propertyName, String generalType){
		ObjectPropertyHolder oph = getProperty(properties, propertyName);
		if(oph != null){
			if(oph.getPropertyGeneralType().equals(generalType)) return true;
		}
		return false;
	}
	
	public static boolean hasEnumValues(ObjectPropertyHolder property, String... values){
		assertNotNull(property);
		assertNotNull(values);
		
		if(property.getObjectPropertyDescriptor() instanceof EnumObjectPropertyDescriptor){
			EnumObjectPropertyDescriptor descriptor = (EnumObjectPropertyDescriptor) property.getObjectPropertyDescriptor();
			List<String> incomingValues = new ArrayList<String>(Arrays.asList(values));
			for(String value : incomingValues){
				if(!descriptor.getValues().contains(value))
					return false;
			}
			return true;
		}
		return false;
	}
}
