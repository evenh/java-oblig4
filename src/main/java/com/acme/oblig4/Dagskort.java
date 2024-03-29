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

public class Dagskort extends Reisekort {
	public final static int DAGSPRIS = 75;
	private int saldo;
	private static int antallSolgte = 0;
	private static int sumAlleDagskort = 0;


	public Dagskort(){
		super(DAGSPRIS); // Bruk av this gir feilmelding
		this.saldo = this.DAGSPRIS;
		this.antallSolgte++;
		this.sumAlleDagskort += this.DAGSPRIS;
	}

	public static int getSumAlleDagskort(){
		return sumAlleDagskort;
	}

	public static int getAntallSolgte(){
		return antallSolgte;
	}

	// Alias til gyldig()
	public boolean valider(){
		return this.gyldig();
	}

	public boolean gyldig(){
		if(super.gyldig()){
			return true;
		}
		if(saldo >= this.DAGSPRIS){
			// Ny kalender
			Calendar utlop = Calendar.getInstance();
			// Legg til en dag
			utlop.add(Calendar.DATE, 1);
			// Oppdater utløpsdato
			setUtlopstidspunkt(utlop);
			// Trekk penger og sett gyldig billett
			saldo -= this.DAGSPRIS;

			return true;
		}

		return false;
	}
}
