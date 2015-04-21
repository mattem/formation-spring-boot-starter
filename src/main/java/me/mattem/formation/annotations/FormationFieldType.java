package me.mattem.formation.annotations;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FormationFieldType {
	AS_CLASS(""),
	TEXT_AREA("textarea"),
	COLOR("color"),
	DATE("data"),
	DATETIME("datetime"),
	DATETIME_LOCAL("datetime-local"),
	MONTH("month"),
	TELEPHONE("tel"),
	TIME("time"),
	WEEK("week");
	
	String name;
	private FormationFieldType(String name) {
		this.name = name;
	}
	
	@Override @JsonValue
	public String toString(){
		return name;
	}
}