package me.mattem.formation.calibration;

import java.util.List;
import java.util.Map;

import me.mattem.formation.annotations.FormationInclude;

@FormationInclude
public class FormationMapCalibration {
	
	public Map<String, String> stringStringMap;
	public Map<Integer, Integer> integerIntegerMap;
	public Map<Integer, List<String>> integerListMap;
	
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
}
