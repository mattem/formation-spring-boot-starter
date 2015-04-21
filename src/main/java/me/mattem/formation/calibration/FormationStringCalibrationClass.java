package me.mattem.formation.calibration;

import me.mattem.formation.annotations.FormationField;
import me.mattem.formation.annotations.FormationFieldType;
import me.mattem.formation.annotations.FormationInclude;

@FormationInclude
public class FormationStringCalibrationClass {
	private String simpleString;
	private String simpleRequiredString;
	private String overrideString;
	private String color;
	private String dateTime;
	private String week;
	private String textArea;
	
	public String getSimpleString() {
		return simpleString;
	}
	public void setSimpleString(String simpleString) {
		this.simpleString = simpleString;
	}
	@FormationField(required=true, views={"RequiredFields", "SimpleProperties"})
	public String getSimpleRequiredString() {
		return simpleRequiredString;
	}
	public void setSimpleRequiredString(String simpleRequiredString) {
		this.simpleRequiredString = simpleRequiredString;
	}
	@FormationField(overrideAs=FormationStringCalibrationClass.class)
	public String getOverrideString() {
		return overrideString;
	}
	public void setOverrideString(String overrideString) {
		this.overrideString = overrideString;
	}
	@FormationField(fieldType=FormationFieldType.COLOR)
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@FormationField(fieldType=FormationFieldType.DATETIME_LOCAL)
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	@FormationField(fieldType=FormationFieldType.WEEK)
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	@FormationField(fieldType=FormationFieldType.TEXT_AREA)
	public String getTextArea() {
		return textArea;
	}
	public void setTextArea(String textArea) {
		this.textArea = textArea;
	}

}
