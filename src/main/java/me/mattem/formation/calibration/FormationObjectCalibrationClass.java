package me.mattem.formation.calibration;

import java.util.List;

import me.mattem.formation.annotations.FormationInclude;

@FormationInclude
public class FormationObjectCalibrationClass {

	private Object object;
	private List<Object> objectList;

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public List<Object> getObjectList() {
		return objectList;
	}

	public void setObjectList(List<Object> objectList) {
		this.objectList = objectList;
	}
}
