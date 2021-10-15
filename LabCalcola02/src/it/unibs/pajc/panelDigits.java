package it.unibs.pajc;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class panelDigits extends pnlOpBase {

	public panelDigits() {
		
		for(int i=0;i<10;i++) {
			addButton(""+i);
		}
		

	}
	
	
}
