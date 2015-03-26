package me.mattem.formation.scanners;

import java.util.ArrayList;
import java.util.List;

public class FormationObjectScannerResult {
	private Long scanTime = 0L;
	public Long getScanTime(){ return this.scanTime; }
	public void setScanTime(Long scanTime){ this.scanTime = scanTime; }
	
	private int candidatesScanned = 0;
	public int getCandidatesScanned() { return candidatesScanned; }
	public void setCandidatesScanned(int candidatesScanned) { this.candidatesScanned = candidatesScanned; }
	public int incrementCandidatesScanned(){ this.candidatesScanned++; return candidatesScanned; }

	private int candidatesRejected = 0;
	public int getCandidatesRejected() { return candidatesRejected; }
	public void setCandidatesRejected(int candidatesRejected) { this.candidatesRejected = candidatesRejected; }	
	public int incrementCandidatesRejected(){ this.candidatesRejected++; return candidatesRejected; }
	
	private List<Class<?>> classes = new ArrayList<Class<?>>();
	public void addClass(Class<?> clazz){ this.classes.add(clazz); }
	public List<Class<?>> getClasses(){ return classes; }
}