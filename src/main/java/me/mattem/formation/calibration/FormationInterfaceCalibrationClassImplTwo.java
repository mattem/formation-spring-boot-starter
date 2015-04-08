package me.mattem.formation.calibration;

import me.mattem.formation.annotations.FormationInclude;

@FormationInclude(typeCategories="Calibration")
public class FormationInterfaceCalibrationClassImplTwo implements FormationInterfaceCalibrationInterface {
	private int number;
	private boolean bool;
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public boolean isBool() {
		return bool;
	}
	public void setBool(boolean bool) {
		this.bool = bool;
	}
}
