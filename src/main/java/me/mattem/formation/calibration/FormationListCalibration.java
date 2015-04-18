package me.mattem.formation.calibration;

import java.util.List;
import java.util.Map;

import me.mattem.formation.annotations.FormationExclude;
import me.mattem.formation.annotations.FormationInclude;

@FormationInclude
public class FormationListCalibration {
	//public List<?> genericList;
	private List<String> stringList;
	private List<Map<String, String>> mapList;
	private List<List<String>> listStringList;
	private List<FormationListCalibration> recursiveList;
	private List<FormationInterfaceCalibrationClass> interfaceList;
	
	private String debugString;

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
	
	public List<FormationListCalibration> getRecursiveList() {
		return recursiveList;
	}

	public void setRecursiveList(List<FormationListCalibration> recursiveList) {
		this.recursiveList = recursiveList;
	}

	public String getDebugString() {
		return debugString;
	}

	public void setDebugString(String debugString) {
		this.debugString = debugString;
	}

	@FormationExclude // This currently wont work
	public List<FormationInterfaceCalibrationClass> getInterfaceList() {
		return interfaceList;
	}

	public void setInterfaceList(List<FormationInterfaceCalibrationClass> interfaceList) {
		this.interfaceList = interfaceList;
	}
	
}
