package com.acme.oblig4;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Calendar;
import java.text.DateFormat;

public class KlippekortTest
{
	Klippekort kk = new Klippekort(Klippekort.PRIS_PER_REISE);

    @Test
    public void standardPrisHarRiktigPris(){
		assertEquals(Klippekort.PRIS_PER_REISE, kk.getPris());
    }

    @Test
    public void harRiktigStatistikk(){
		int yUWork = 6;

		assertEquals(1*yUWork, kk.getAntallSolgte());
    	assertEquals((Klippekort.PRIS_PER_REISE*yUWork)+500, kk.getSumAlleKlippekort());
    }

    @Test
    public void harRiktigSaldo(){
    	assertEquals(Klippekort.PRIS_PER_REISE, kk.getSaldo());
    }

    @Test
    public void harGyldigBillett(){
    	assertEquals(true, kk.gyldig());
    }

    @Test
    public void kanLadeOppMed500kr(){
    	kk.ladOpp(500);
    	assertEquals(Klippekort.PRIS_PER_REISE+500, kk.getSaldo());
    }

    @Test
	public void sjekkKorrektUtlopsTid(){
    	// Sett opp vår kalender
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, 1);
		DateFormat tf = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM);
		String testTid = tf.format(c.getTime());
    	// Valider billetten
		kk.valider();
    	assertEquals(testTid, kk.gyldigTil());
	}

	@Test
	public void sjekkDagskortUgyldigEtterEttDogn(){
    	// Sett opp vår kalender
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, 2);
		DateFormat tf = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM);
		String testTid = tf.format(c.getTime());
    	// Valider billetten
		kk.valider();
    	assertThat((testTid == kk.gyldigTil()), is(false));
	}
}
