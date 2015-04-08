package me.mattem.formation.calibration;

import me.mattem.formation.annotations.FormationInclude;

@FormationInclude
public class FormationBooleanCalibrationClass {
	private boolean isSomething;
	private Boolean isSomethingBoxed;
	
	public boolean isSomething() {
		return isSomething;
	}
	public void setSomething(boolean isSomething) {
		this.isSomething = isSomething;
	}
	public Boolean getIsSomethingBoxed() {
		return isSomethingBoxed;
	}
	public void setIsSomethingBoxed(Boolean isSomethingBoxed) {
		this.isSomethingBoxed = isSomethingBoxed;
	}
}
