package com.acme.oblig4;

/*
 * Oblig 4 (DAPE1400) - forfattet av følgende
 * studenter fra Anvendt datateknologi, 2. året:
 *
 * Vegar Norman <s189153@stud.hioa.no>
 * Per Erik Finstad <s189138@stud.hioa.no>
 * Even Holthe <s189124@stud.hioa.no>
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Reisekortsalg extends JFrame {
	// Attributter
	private static final int KLIPP = 1, DAG = 2, MAANED = 3;
	private JTextField kortNrFelt, betalingsFelt, belopsFelt;
	private JButton klipp, dag, mnd, ladeknapp;
	private JTextArea info;
	private Lytter lytter;
	private ReisekortSystem kortsystem;

	public Reisekortsalg(ReisekortSystem k){
		super("KORTSALG");
		this.kortsystem = k;
		this.lytter = new Lytter();

		// Komponentene
		this.kortNrFelt    = new JTextField(7);
		this.betalingsFelt = new JTextField(7);
		this.belopsFelt    = new JTextField(7);

		this.klipp         = new JButton("Klippekort");
		this.dag           = new JButton("Dagskort");
		this.mnd           = new JButton("Månedskort");
		this.ladeknapp     = new JButton("Oppladning av klippekort");

		// Etter litt testing, så ser vi at denne er nødvendig
		this.info = (System.getProperty("os.name").toLowerCase().indexOf("windows")!= -1) ? new JTextArea(4, 30) : new JTextArea(4, 31);

		this.info.setEditable(false);
		this.betalingsFelt.setEditable(false);
		this.betalingsFelt.setOpaque(false);

		// Assigner lytter
		this.klipp.addActionListener(this.lytter);
		this.dag.addActionListener(this.lytter);
		this.mnd.addActionListener(this.lytter);
		this.ladeknapp.addActionListener(this.lytter);

		this.klipp.setActionCommand("klipp");
		this.dag.setActionCommand("dag");
		this.mnd.setActionCommand("mnd");
		this.ladeknapp.setActionCommand("ladeknapp");

		// Fyll infoboksen med priser
		this.info.setText("Klippekort:\tkr "+Klippekort.PRIS_PER_REISE+",-\nDagskort:\tkr "+Dagskort.DAGSPRIS+",-\nMånedskort:\tkr "+Maanedskort.MAANEDSPRIS+",-");

		// Plassering
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		c.add(this.info);
		c.add(this.klipp);
		c.add(this.dag);
		c.add(this.mnd);

		c.add(new JLabel("Beløp: "));
		c.add(belopsFelt);
		c.add(this.ladeknapp);

		c.add(new JLabel("Reisekortnr: "));
		c.add(this.kortNrFelt);
		c.add(new JLabel("Betal kr: "));
		c.add(this.betalingsFelt);

		// Vindu
		if(System.getProperty("os.name").toLowerCase().indexOf("windows")!= -1){
			setSize(350, 220);
		} else {
			// Mac
			setSize(375, 210);
		}

		// Synlighet og lukkbar
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void nyttReisekort(int kortType){
		Reisekort kort = null;

		switch(kortType){
			case KLIPP:
				// Validering av input
			try {
				kort = new Klippekort(Integer.parseInt(this.belopsFelt.getText()));
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "Du må taste inn et gyldig beløp (heltall)!");
				return;
			}
			break;

			case DAG:
			kort = new Dagskort();
			break;

			case MAANED:
			kort = new Maanedskort();
			break;
		}

		// Aktiviser kortet
		this.kortsystem.settInnReisekort(kort);
		this.betalingsFelt.setText(kort.getPris()+",-");
		this.kortNrFelt.setText(Integer.toString(kort.getKortNr()));
	}

	public void ladOppKlippekort(){
		try {
			int kortNr = Integer.parseInt(this.kortNrFelt.getText());
			int sum    = Integer.parseInt(this.belopsFelt.getText());

			Klippekort kort = this.kortsystem.ladOppKlippekort(kortNr, sum);

			if(kort != null){
				this.betalingsFelt.setText(sum+",-");
				JOptionPane.showMessageDialog(null, "Ny saldo er kr "+kort.getSaldo()+",-");
			} else {
				this.betalingsFelt.setText("error");
			}
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "Du har ikke spesifisert et gyldig kortnummer!");
		}
	}

	private class Lytter implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()){
				case "klipp":
				nyttReisekort(KLIPP);
				break;

				case "dag":
				nyttReisekort(DAG);
				break;

				case "mnd":
				nyttReisekort(MAANED);
				break;

				case "ladeknapp":
				ladOppKlippekort();
				break;
			}
		}
	}

}
