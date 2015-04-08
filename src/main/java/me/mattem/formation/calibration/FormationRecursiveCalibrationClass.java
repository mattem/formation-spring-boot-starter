package me.mattem.formation.calibration;

import me.mattem.formation.annotations.FormationInclude;

@FormationInclude
public class FormationRecursiveCalibrationClass {
	
	private String name;
	private FormationRecursiveCalibrationClass recursiveCalibrationClass;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public FormationRecursiveCalibrationClass getRecursiveCalibrationClass() {
		return recursiveCalibrationClass;
	}
	public void setRecursiveCalibrationClass(FormationRecursiveCalibrationClass recursiveCalibrationClass) {
		this.recursiveCalibrationClass = recursiveCalibrationClass;
	}
	
}
