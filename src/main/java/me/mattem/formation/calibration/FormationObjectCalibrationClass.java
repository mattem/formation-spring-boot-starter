package me.mattem.formation.calibration;

import java.util.Date;
import java.util.List;
import java.util.Map;

import me.mattem.formation.annotations.FormationInclude;

@FormationInclude
public class FormationObjectCalibrationClass {

	private Object object;
	private List<Object> objectList;
	private Map<String, Object> objectMap;
	
	private Date date;

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

	public Map<String, Object> getObjectMap() {
		return objectMap;
	}

	public void setObjectMap(Map<String, Object> objectMap) {
		this.objectMap = objectMap;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
