package me.mattem.formation.calibration;

import java.util.List;
import java.util.Map;

import me.mattem.formation.annotations.FormationInclude;

@FormationInclude
public class FormationMapCalibration {
	
	private Map<String, String> stringStringMap;
	private Map<Integer, Integer> integerIntegerMap;
	private Map<Integer, List<String>> integerListMap;
	
	// Interesting things in maps
	private Map<FormationEnumCalibration, String> enumStringMap;
	
	public Map<String, String> getStringStringMap() {
		return stringStringMap;
	}
	public void setStringStringMap(Map<String, String> stringStringMap) {
		this.stringStringMap = stringStringMap;
	}
	public Map<Integer, Integer> getIntegerIntegerMap() {
		return integerIntegerMap;
	}
	public void setIntegerIntegerMap(Map<Integer, Integer> integerIntegerMap) {
		this.integerIntegerMap = integerIntegerMap;
	}
	public Map<Integer, List<String>> getIntegerListMap() {
		return integerListMap;
	}
	public void setIntegerListMap(Map<Integer, List<String>> integerListMap) {
		this.integerListMap = integerListMap;
	}
	public Map<FormationEnumCalibration, String> getEnumStringMap() {
		return enumStringMap;
	}
	public void setEnumStringMap(Map<FormationEnumCalibration, String> enumStringMap) {
		this.enumStringMap = enumStringMap;
	}
}
