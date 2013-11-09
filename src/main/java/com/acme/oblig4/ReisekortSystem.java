package com.acme.oblig4;

/*
 * Oblig 4 (DAPE1400) - forfattet av følgende
 * studenter fra Anvendt datateknologi, 2. året:
 *
 * Vegar Norman <s189153@stud.hioa.no>
 * Per Erik Finstad <s189138@stud.hioa.no>
 * Even Holthe <s189124@stud.hioa.no>
 */

public class ReisekortSystem {
	public static final int ANTALL = 100;
	public static final int UTVIDELSE = 10;
	private Reisekort[] reisekortene;

	public ReisekortSystem(){
		reisekortene = new Reisekort[this.ANTALL];
	}

	public Reisekort finnReisekort(int kortNr){
		for(int i=0;i<this.reisekortene.length;i++){
			if(this.reisekortene[i] != null && this.reisekortene[i].getKortNr() == kortNr){
				return reisekortene[i];
			}
		}
		return null;
	}

	public int getAntallReisekort(){
		return this.reisekortene.length;
	}

	public String foo(){
		String y = "";
		for(Reisekort x : this.reisekortene){
			y += x.getKortNr() + "\n";
		}
		return y;
	}

	public void utvidArray(){
		// Opprett et midlertidig array
		Reisekort[] tmp = new Reisekort[(this.reisekortene.length + this.UTVIDELSE)];
		// Loop gjennom vårt gjeldende array og overfør informasjon
		for(int i=0;i<this.reisekortene.length;i++){
			tmp[i] = this.reisekortene[i];
		}
		// Bytt ut vårt gamle array med vårt nye
		this.reisekortene = tmp;
	}

	public void settInnReisekort(Reisekort rk){
		// Hvis reisekortet allerede eksisterer, gjør ingenting
		if(this.finnReisekort(rk.getKortNr()) != null){
			return;
		} else {
			// Loop gjennom reisekortene og se etter ledig plass
			// Hvis ledig plass, sett inn og avslutt
			for(int i=0;i<this.reisekortene.length;i++){
				if(this.reisekortene[i] == null){
					this.reisekortene[i] = rk;
					return;
				}
			}
		}
		// Hvis vi kom hit, er det ikke ledig plass. Utvid
		// arrayet og forsøk på nytt
		this.utvidArray();
		this.settInnReisekort(rk);
	}

	public Klippekort ladOppKlippekort(int kortNr, int belop){
		Reisekort kort = this.finnReisekort(kortNr);

		if(kort instanceof Klippekort){
			// Må castes for å kalle riktig metode,
			// samt returnere riktig datatype
			((Klippekort) kort).ladOpp(belop);
			return (Klippekort) kort;
		}

		return null;
	}

	public static String inntjeningsInfo(){
		return "Må implementeres";
	}
}
