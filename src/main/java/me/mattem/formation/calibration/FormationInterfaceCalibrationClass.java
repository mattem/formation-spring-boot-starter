package me.mattem.formation.calibration;

import me.mattem.formation.annotations.FormationInclude;
import me.mattem.formation.annotations.FormationInterface;

@FormationInclude
public class FormationInterfaceCalibrationClass {
	private FormationInterfaceCalibrationInterface calibrationInterface;

	@FormationInterface(typeCategory="Calibration")
	public FormationInterfaceCalibrationInterface getCalibrationInterface() {
		return calibrationInterface;
	}

	public void setCalibrationInterface(FormationInterfaceCalibrationInterface calibrationInterface) {
		this.calibrationInterface = calibrationInterface;
	}
	
	
}
