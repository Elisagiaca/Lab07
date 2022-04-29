package it.polito.tdp.poweroutages.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	private List<Nerc> nercList;
	
	List<PowerOutages> eventList;
	List<PowerOutages> listaMigliore;
	
	private int persone;
	
	public Model() {
		podao = new PowerOutageDAO();
		nercList = podao.getNercList();
	}
	
	//INIZIALIZZAZIONE RICORSIONE
	public List<PowerOutages> trovaSequenza(int maxYears, int maxHours, Nerc nerc){
		listaMigliore = new ArrayList<>();
		persone = 0;
		eventList = podao.getNercSelezionati(nerc);
		Collections.sort(eventList);
		
		cerca(new ArrayList<PowerOutages>(), maxYears, maxHours);
		
		return listaMigliore;
	}
	
	//RICORSIONE
	public void cerca(List<PowerOutages> parziale, int maxY, int maxH) {
		
		//CASO TERMINALE
		//listaMigliore, persone
		if (persone < this.sommaPersone(parziale)) {
			listaMigliore = new ArrayList<>(parziale);
			persone =  this.sommaPersone(parziale);
		}

		//CASO NORMALE
		for(PowerOutages event : eventList) {
			if (parziale.contains(event)==false) {
				
				//ADD
				parziale.add(event);
				
				
				//CERCA
				//controlli
				if((controllaAnni(parziale, maxY)==true) && (controllaOre(parziale, maxH)==true)) {
					cerca(parziale, maxY, maxH);
				}
				
				//REMOVE (BACKTRACKING)
				parziale.remove(event);
				
			}
		}
	}
	
	public int sommaPersone(List<PowerOutages> parziale){
		int somma = 0;
		for(PowerOutages p : parziale) {
			somma = somma + p.getCustomers_affected();
		}
		return somma;
	}
	
	public boolean controllaAnni(List<PowerOutages> parziale, int y) {
		if(parziale.size() >= 2) {
			if (parziale.get(parziale.size()-1).getYear() - parziale.get(0).getYear() > y) {
				return false;
			}
		}
		return true;
		
	}
	
	
	public long calcolaOre(List<PowerOutages> parziale) {
		long somma = 0;
		for(PowerOutages p : parziale) {
			somma = somma + p.getDurata();
		}
		return somma;
	}
	public boolean controllaOre(List<PowerOutages> parziale, int o) {
		long tot = calcolaOre(parziale);
		if (tot > o) {
			return false;
		}
		return true;
	}
	
	public List<Nerc> getNercList() {
		return this.nercList;
	}
}
