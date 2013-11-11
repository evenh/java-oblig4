package com.acme.oblig4;

/*
 * Oblig 4 (DAPE1400) - forfattet av følgende
 * studenter fra Anvendt datateknologi, 2. året:
 *
 * Vegar Norman <s189153@stud.hioa.no>
 * Per Erik Finstad <s189138@stud.hioa.no>
 * Even Holthe <s189124@stud.hioa.no>
 */

import java.util.Calendar;

public class Klippekort extends Reisekort {
	public static final int PRIS_PER_REISE = 28;
	private int saldo;

	// Det totale antall solgte klippekort
	private static int antallSolgte = 0;
	// Den totale summen som er satt inn på alle klippekortene til sammen.
	private static int sumAlleKlippekort = 0;

	public Klippekort(int belop){
		// Kall på Reisekort sin konstruktør
		super(belop);
		// Oppdater saldo med beløp
		this.saldo = belop;
		// Statistikk
		this.antallSolgte++;
		this.sumAlleKlippekort += belop;
	}

	public int getSaldo(){
		return this.saldo;
	}

	public int getAntallSolgte(){
		return this.antallSolgte;
	}

	public int getSumAlleKlippekort(){
		return this.sumAlleKlippekort;
	}

	// Alias til gyldig()
	public boolean valider(){
		return this.gyldig();
	}

	public boolean gyldig(){
		// Hvis Reisekort sier den er gyldig, er alt fint
		if(super.gyldig()){
			return true;
		}
		if(saldo >= this.PRIS_PER_REISE){
			// Ny kalender
			Calendar utlop = Calendar.getInstance();
			// Legg til en time
			utlop.add(Calendar.HOUR_OF_DAY, 1);
			// Oppdater utløpsdato
			setUtlopstidspunkt(utlop);
			// Trekk penger og sett gyldig billett
			saldo -= this.PRIS_PER_REISE;

			return true;
		}

		return false;
	}

	public void ladOpp(int belop){
		this.saldo             += belop;
		this.sumAlleKlippekort += belop;
	}
}
