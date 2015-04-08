package me.mattem.formation.calibration;

import java.util.List;
import java.util.Map;

import me.mattem.formation.annotations.FormationInclude;

@FormationInclude
public class FormationListCalibration {
	//public List<?> genericList;
	public List<String> stringList;
	public List<Map<String, String>> mapList;
	public List<List<String>> listStringList;

	public List<String> getStringList() {
		return stringList;
	}

	public void setStringList(List<String> stringList) {
		this.stringList = stringList;
	}
	
	public List<Map<String, String>> getMapList() {
		return mapList;
	}

	public void setMapList(List<Map<String, String>> mapList) {
		this.mapList = mapList;
	}

	public List<List<String>> getListStringList() {
		return listStringList;
	}

	public void setListStringList(List<List<String>> listStringList) {
		this.listStringList = listStringList;
	}
	
}
