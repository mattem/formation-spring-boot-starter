package me.mattem.formation.cache.objectdescriptors;

import me.mattem.formation.annotations.FormationInclude;
import me.mattem.formation.configuration.FormationDefaults;

@FormationInclude
public class MapObjectPropertyDescriptor extends AbstractObjectPropertyDescriptor {
	private String mapKeyLabel = FormationDefaults.MAP_KEY_LABEL;
	public String getMapKeyLabel(){ return this.mapKeyLabel; }
	public void setMapKeyLabel(String mapKeyLabel){ this.mapKeyLabel = mapKeyLabel; }
	
	private String mapValueLabel = FormationDefaults.MAP_VALUE_LABEL;
	public String getMapValueLabel(){ return this.mapValueLabel; }
	public void setMapValueLabel(String mapValueLabel){ this.mapValueLabel = mapValueLabel; }
}
