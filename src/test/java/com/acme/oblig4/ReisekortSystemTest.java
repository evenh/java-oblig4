package com.acme.oblig4;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.hamcrest.CoreMatchers.*;

public class ReisekortSystemTest {
	ReisekortSystem rs = new ReisekortSystem();

    @Test
    public void opprettReisekortSystem(){
    	assertEquals(true, (rs instanceof ReisekortSystem));
    }

    @Test 
    public void settInn105Reisekort(){
    	for(int i=0;i<(rs.ANTALL+5);i++){
    		Klippekort kk = new Klippekort(Klippekort.PRIS_PER_REISE);
    		rs.settInnReisekort(kk);
    	}
    	assertEquals(110, rs.getAntallReisekort());
    }

    @Test
    public void settInn_Finn_Lad_Reisekort(){
    	// Sett inn og finn
    	Klippekort kort = new Klippekort(Klippekort.PRIS_PER_REISE);
    	int kortNr = kort.getKortNr();
    	rs.settInnReisekort(kort);
    	assertEquals(kort, rs.finnReisekort(kortNr));

    	// Lad opp og verifiser
    	Klippekort ferdigKort = rs.ladOppKlippekort(kortNr, 250);
    	assertEquals((Klippekort.PRIS_PER_REISE+250), ferdigKort.getSaldo());
    }
}
