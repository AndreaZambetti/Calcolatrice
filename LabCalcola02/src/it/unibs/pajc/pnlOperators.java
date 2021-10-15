package it.unibs.pajc;

import java.util.Set;

import javax.swing.JPanel;

public class pnlOperators extends pnlOpBase {
	
	public pnlOperators() {
	}
	
	public void addOperators(Set<Character> ops) {
		for(Character c: ops)
			addButton(c+"");
	}
	
}
