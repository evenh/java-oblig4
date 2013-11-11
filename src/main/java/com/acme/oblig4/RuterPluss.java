package com.acme.oblig4;

/*
 * Oblig 4 (DAPE1400) - forfattet av følgende
 * studenter fra Anvendt datateknologi, 2. året:
 *
 * Vegar Norman <s189153@stud.hioa.no>
 * Per Erik Finstad <s189138@stud.hioa.no>
 * Even Holthe <s189124@stud.hioa.no>
 */

public class RuterPluss {
    public static void main( String[] args ){
    	ReisekortSystem rs = new ReisekortSystem();

        Reisekortsalg w1 = new Reisekortsalg(rs);
        Kontrollvindu w2 = new Kontrollvindu(rs);
        Administrasjonsvindu w3 = new Administrasjonsvindu();
    }
}
