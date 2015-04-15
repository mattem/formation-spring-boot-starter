package me.mattem.formation.calibration;

import me.mattem.formation.annotations.FormationInclude;

@FormationInclude
public enum FormationEnumCalibration {
	ITEM_ONE("One"),
	ITEM_TWO("Two"),
	ITEM_THREE("Three");
	
	private String name;
	private FormationEnumCalibration(String name) {
		this.name = name;
	}
	
	@Override
	public String toString(){
		return name;
	}
}
