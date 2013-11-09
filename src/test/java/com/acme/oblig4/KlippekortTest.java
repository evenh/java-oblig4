package com.acme.oblig4;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.hamcrest.CoreMatchers.*;

public class KlippekortTest
{
	Klippekort kk = new Klippekort(Klippekort.PRIS_PER_REISE);

    @Test
    public void standardPrisHarRiktigPris(){
		assertEquals(Klippekort.PRIS_PER_REISE, kk.getPris());
    }

    @Test
    public void harRiktigStatistikk(){
		int yUWork = 4;

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
}
