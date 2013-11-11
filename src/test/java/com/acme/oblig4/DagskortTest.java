package com.acme.oblig4;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Calendar;
import java.text.DateFormat;

public class DagskortTest {
	Dagskort dk = new Dagskort();

	@Test
	public void sjekkStatistikk(){
		int tester = 3;
		assertEquals(1*tester, Dagskort.getAntallSolgte());
		assertEquals(Dagskort.DAGSPRIS*tester, Dagskort.getSumAlleDagskort());
	}

	@Test
	public void sjekkKorrektUtlopsTid(){
    	// Sett opp vår kalender
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		DateFormat tf = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM);
		String testTid = tf.format(c.getTime());
    	// Valider billetten
		dk.valider();
    	assertEquals(testTid, dk.gyldigTil());
	}

	@Test
	public void sjekkDagskortUgyldigEtterEttDogn(){
    	// Sett opp vår kalender
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		c.add(Calendar.HOUR, 1);
		DateFormat tf = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM);
		String testTid = tf.format(c.getTime());
    	// Valider billetten
		dk.valider();
    	assertThat((testTid == dk.gyldigTil()), is(false));
	}

}
