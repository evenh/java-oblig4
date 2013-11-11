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

public class Administrasjonsvindu extends JFrame implements ActionListener {
	// Attributter
	private JTextArea output;
	private JButton salgsinfo;

	public Administrasjonsvindu(){
		// Kall på JFrames konstruktør
		super("ADMINISTRASJON");
		// Lag en Container med FlowLayout
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		// JButton-objekt
		this.salgsinfo = new JButton("Salgsinformasjon");
		this.salgsinfo.addActionListener(this);
		c.add(salgsinfo);
		// JTextArea-objekt
		this.output = new JTextArea(10, 20);
		c.add(output);

		// Dimensjoner
		if(System.getProperty("os.name").toLowerCase().indexOf("windows")!= -1){
			setSize(230, 200);
		} else {
			setSize(250, 200);
		}

		// Synlighet og lukkbar
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		this.output.setText(ReisekortSystem.inntjeningsInfo());
	}
}
