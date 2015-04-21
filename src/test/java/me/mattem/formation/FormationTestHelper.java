package me.mattem.formation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.mattem.formation.cache.ObjectPropertyDescriptor;
import me.mattem.formation.cache.objectdescriptors.EnumObjectPropertyDescriptor;

public final class FormationTestHelper {

	private FormationTestHelper(){};
	
	public static ObjectPropertyDescriptor getProperty(List<ObjectPropertyDescriptor> properties, String propertyName){
		assertNotNull(propertyName);
		assertNotNull(properties);

		for(ObjectPropertyDescriptor prop : properties){
			if(prop.getProperyName().equals(propertyName)){
				return prop;
			}
		}
		return null;
	}
	
	public static boolean hasProperty(List<ObjectPropertyDescriptor> properties, String propertyName){
		if(getProperty(properties, propertyName) == null) return false;
		return true;
	}
	
	public static boolean hasPropertyOfType(List<ObjectPropertyDescriptor> properties, String propertyName, String generalType){
		ObjectPropertyDescriptor oph = getProperty(properties, propertyName);
		if(oph != null){
			if(oph.getPropertyGeneralType().equals(generalType)) return true;
		}
		return false;
	}
	
	public static boolean hasEnumValues(ObjectPropertyDescriptor property, String... values){
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
