package me.mattem.formation.calibration;

import me.mattem.formation.annotations.FormationInclude;

@FormationInclude
public class FormationCalibrationClass {
	private FormationStringCalibrationClass stringCalibrationClass;
	private FormationBooleanCalibrationClass booleanCalibrationClass;
	private FormationListCalibration listCalibration;
	private FormationMapCalibration mapCalibration;
	private FormationInterfaceCalibrationClass interfaceCalibration;
	private FormationRecursiveCalibrationClass recursiveCalibrationClass;
	
	public FormationStringCalibrationClass getStringCalibrationClass() {
		return stringCalibrationClass;
	}
	public void setStringCalibrationClass(FormationStringCalibrationClass stringCalibrationClass) {
		this.stringCalibrationClass = stringCalibrationClass;
	}
	public FormationBooleanCalibrationClass getBooleanCalibrationClass() {
		return booleanCalibrationClass;
	}
	public void setBooleanCalibrationClass(FormationBooleanCalibrationClass booleanCalibrationClass) {
		this.booleanCalibrationClass = booleanCalibrationClass;
	}
	public FormationListCalibration getListCalibration() {
		return listCalibration;
	}
	public void setListCalibration(FormationListCalibration listCalibration) {
		this.listCalibration = listCalibration;
	}
	public FormationMapCalibration getMapCalibration() {
		return mapCalibration;
	}
	public void setMapCalibration(FormationMapCalibration mapCalibration) {
		this.mapCalibration = mapCalibration;
	}
	public FormationInterfaceCalibrationClass getInterfaceCalibration() {
		return interfaceCalibration;
	}
	public void setInterfaceCalibration(FormationInterfaceCalibrationClass interfaceCalibration) {
		this.interfaceCalibration = interfaceCalibration;
	}
	public FormationRecursiveCalibrationClass getRecursiveCalibrationClass() {
		return recursiveCalibrationClass;
	}
	public void setRecursiveCalibrationClass(FormationRecursiveCalibrationClass recursiveCalibrationClass) {
		this.recursiveCalibrationClass = recursiveCalibrationClass;
	}
}
