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
		
		this.info          = new JTextArea(5, 32);

		this.info.setEditable(false);
		this.betalingsFelt.setEditable(false);
		this.betalingsFelt.setOpaque(false); 

		// Assigner lytter
		this.klipp.addActionListener(this.lytter);
		this.dag.addActionListener(this.lytter);
		this.mnd.addActionListener(this.lytter);
		this.ladeknapp.addActionListener(this.lytter);

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
			setSize(360, 220);
		} else {
			// Mac
			setSize(380, 220);
		}

		// Synlighet og lukkbar
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public class Lytter implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}

}
