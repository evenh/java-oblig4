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

public class Maanedskort extends Reisekort {
	public final static int MAANEDSPRIS = 1040;
	private int saldo;
	private static int antallSolgte = 0;
	private static int sumAlleDagskort = 0;


	public Maanedskort(){
		super(MAANEDSPRIS); // Bruk av this gir feilmelding
		this.saldo = this.MAANEDSPRIS;
		this.antallSolgte++;
		this.sumAlleDagskort += this.MAANEDSPRIS;
	}

	public static int getSumAlleMaanedskort(){
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
		if(saldo >= this.MAANEDSPRIS){
			// Ny kalender
			Calendar utlop = Calendar.getInstance();
			// Legg til en dag
			utlop.add(Calendar.MONTH, 1);
			// Oppdater utløpsdato
			setUtlopstidspunkt(utlop);
			// Trekk penger og sett gyldig billett
			saldo -= this.MAANEDSPRIS;

			return true;
		}

		return false;
	}
}
