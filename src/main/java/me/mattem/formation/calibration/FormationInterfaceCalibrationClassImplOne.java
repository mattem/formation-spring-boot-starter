package me.mattem.formation.calibration;

import me.mattem.formation.annotations.FormationInclude;

@FormationInclude(typeCategories="Calibration")
public class FormationInterfaceCalibrationClassImplOne implements FormationInterfaceCalibrationInterface {
	private String string;
	private boolean bool;
	
	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}
	public boolean isBool() {
		return bool;
	}
	public void setBool(boolean bool) {
		this.bool = bool;
	}
}
