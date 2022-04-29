package it.polito.tdp.poweroutages.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PowerOutages implements Comparable<PowerOutages>{
	
	private int id;
	private Nerc nerc;
	LocalDateTime outageStart;
	LocalDateTime outageEnd;
	private int customers_affected;
	private long durata;
	private int year;
	
	
	//COSTRUTTORE E INIZIALIZZAZIONE ANNI E ORE
	public PowerOutages(int id, Nerc nerc, LocalDateTime outageStart, LocalDateTime outageEnd, int customers_affected) {
		super();
		this.id = id;
		this.nerc = nerc;
		this.outageStart = outageStart;
		this.outageEnd = outageEnd;
		this.customers_affected = customers_affected;
		
		
		this.durata = this.outageStart.until(this.outageEnd, ChronoUnit.HOURS);
		this.year = this.outageStart.getYear();
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Nerc getNerc() {
		return nerc;
	}


	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}


	public LocalDateTime getOutageStart() {
		return outageStart;
	}


	public void setOutageStart(LocalDateTime outageStart) {
		this.outageStart = outageStart;
	}


	public LocalDateTime getOutageEnd() {
		return outageEnd;
	}


	public void setOutageEnd(LocalDateTime outageEnd) {
		this.outageEnd = outageEnd;
	}


	public int getCustomers_affected() {
		return customers_affected;
	}


	public void setCustomers_affected(int customers_affected) {
		this.customers_affected = customers_affected;
	}


	public long getDurata() {
		return durata;
	}


	public void setDurata(long durata) {
		this.durata = durata;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutages other = (PowerOutages) obj;
		if (id != other.id)
			return false;
		return true;
	}


	@Override
	public int compareTo(PowerOutages o) {
		// TODO Auto-generated method stub
		return this.getOutageStart().compareTo(o.getOutageStart());
	}


	@Override
	public String toString() {
		return "PowerOutages [id=" + id + ", nerc=" + nerc + ", outageStart=" + outageStart + ", outageEnd=" + outageEnd
				+ ", customers_affected=" + customers_affected + ", outageDuration=" + durata + ", year=" + year
				+ "]";
	}

	
	
}
