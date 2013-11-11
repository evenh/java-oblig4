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
import java.text.DateFormat;

public abstract class Reisekort {
	private Calendar utlopstidspunkt;
	private int kortNr;
	private static int nesteNr = 1;
	private int pris;

	public Reisekort(int pris){
		this.pris = pris;
		this.kortNr = this.nesteNr++;
	}

	public void setUtlopstidspunkt(Calendar tidspunkt){
		this.utlopstidspunkt = tidspunkt;
	}

	public int getPris(){
		return this.pris;
	}

	public int getKortNr(){
		return this.kortNr;
	}

	public Calendar getUtlopstidspunkt(){
		return this.utlopstidspunkt;
	}

	public String gyldigTil(){
		if(this.getUtlopstidspunkt() == null){
			return null;
		}
		DateFormat tf = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM);
		return tf.format(this.utlopstidspunkt.getTime());
	}

	public boolean gyldig(){
		Calendar naa = Calendar.getInstance();
		return naa.before(this.utlopstidspunkt);
	}
}
