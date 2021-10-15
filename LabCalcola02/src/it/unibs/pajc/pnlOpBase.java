package it.unibs.pajc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class pnlOpBase extends JPanel implements ActionListener {

	public void actionPerformed(ActionEvent e) {
			System.out.println("Bottone premuto: " + 
					((JButton)e.getSource()).getText());
			
			fireActionPerformed(e);
	}
	
	private ArrayList <ActionListener> listenerList = new ArrayList<>();
	
	public void addActionListener(ActionListener listener) {	
		listenerList.add(listener);
	}
	
	private void fireActionPerformed(ActionEvent e) {
		ActionEvent newEvent = new ActionEvent(this,0, ((JButton)e.getSource()).getText());
		
		for(ActionListener l : listenerList) {
			l.actionPerformed(e);
		}		
	}
	
	protected void addButton(String lbl) {
		JButton btn = new JButton(lbl);
		this.add(btn);
		btn.addActionListener(this);
	}
	
	
	

}
