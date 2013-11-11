package com.acme.oblig4;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Calendar;
import java.text.DateFormat;

public class MaanedskortTest {
	Maanedskort mk = new Maanedskort();

	@Test
	public void sjekkStatistikk(){
		int tester = 3;
		assertEquals(1*tester, Maanedskort.getAntallSolgte());
		assertEquals(Maanedskort.MAANEDSPRIS*tester, Maanedskort.getSumAlleMaanedskort());
	}

	@Test
	public void sjekkKorrektUtlopsTid(){
    	// Sett opp vår kalender
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 1);
		DateFormat tf = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM);
		String testTid = tf.format(c.getTime());
    	// Valider billetten
		mk.valider();
    	assertEquals(testTid, mk.gyldigTil());
	}

	@Test
	public void sjekkUgyldigEtterEnMaaned(){
    	// Sett opp vår kalender
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 1);
		c.add(Calendar.HOUR, 1);
		DateFormat tf = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM);
		String testTid = tf.format(c.getTime());
    	// Valider billetten
		mk.valider();
    	assertThat((testTid == mk.gyldigTil()), is(false));
	}

}
