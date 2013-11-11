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

public class Kontrollvindu extends JFrame {
	// Attributter
	private JTextField kortIdFelt;
	private JTextArea display;
	private JButton kontroll;
	private ReisekortSystem kortsystem;

	private Color gul   = new Color(255,255,0);
	private Color rod   = new Color(255,0,0);
	private Color gronn = new Color(0,255,0);

	public Kontrollvindu(ReisekortSystem r){
		// Kall på JFrames konstruktør
		super("BILLETTKONTROLL");

		this.kortsystem = r;

		// Lag en Container med FlowLayout
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		// Label
		c.add(new JLabel("Reisekortnr: "));

		// Boks
		this.kortIdFelt = new JTextField(8);
		c.add(this.kortIdFelt);

		// Knappen
		this.kontroll = new JButton("Billettkontroll");
		this.kontroll.addActionListener(new Lytter());
		c.add(this.kontroll);

		// Textarea
		this.display = new JTextArea(20,28);
		this.display.setBackground(this.gul);
		c.add(this.display);

		// Dimensjoner
		if(System.getProperty("os.name").toLowerCase().indexOf("windows")!= -1){
			setSize(320, 200);
		} else {
			setSize(340, 200);
		}

		// Synlighet og lukkbar
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void kontrollerReisekort(){
		// Finn kort
		Reisekort kort = this.kortsystem.finnReisekort(Integer.parseInt(this.kortIdFelt.getText()));
		// Nullstill output
		this.display.setText("");

		// Hvis kortet ikke er null
		if(kort != null){
			if(kort.gyldig()){
				this.display.setBackground(this.gronn);
				if(kort instanceof Klippekort){
					this.display.append(Klippekort.PRIS_PER_REISE+",- kr har nå blitt trukket fra din reisekonto\n");
					this.display.append("Billetten er nå aktiv. Saldo på reisekonto er "+((Klippekort) kort).getSaldo()+"\n");
				}
				this.display.append("Billetten er gyldig til "+kort.gyldigTil());
			} else {
				this.display.setBackground(this.rod);
				this.display.append("Ugyldig billett\n");
				if(kort instanceof Klippekort){
					this.display.append("En enkeltreise koster"+Klippekort.PRIS_PER_REISE);
					this.display.append(". Din saldo er "+((Klippekort) kort).getSaldo());
				}
			}
		}

		// Hvis kort er null
		this.display.setBackground(this.rod);
		this.display.setText("Ukjent kort");
	}

	public class Lytter implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			kontrollerReisekort();
		}
	}

}
