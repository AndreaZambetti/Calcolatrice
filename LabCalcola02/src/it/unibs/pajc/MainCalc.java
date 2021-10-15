package it.unibs.pajc;

import java.awt.EventQueue;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainCalc {

	private JFrame frame;
	private JTextField txtResult;
	private JPanel panelMainArea;
	private CalcModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainCalc window = new MainCalc();
					window.frame.setVisible(true);// questa creazione del frame

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainCalc() {

		model = new CalcModel();

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 592, 268);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		txtResult = new JTextField("0");
		txtResult.setHorizontalAlignment(SwingConstants.RIGHT);
		txtResult.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		frame.getContentPane().add(txtResult, BorderLayout.NORTH);
		txtResult.setColumns(10);

		panelMainArea = new JPanel();
		panelMainArea.setBackground(Color.BLACK);
		frame.getContentPane().add(panelMainArea, BorderLayout.CENTER);
		panelMainArea.setLayout(null);

		panelDigits pnlDigits = new panelDigits();
		pnlDigits.setBounds(18, 22, 308, 156);
		panelMainArea.add(pnlDigits);
		pnlDigits.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		panelDigits pnlOp = new panelDigits();
		pnlOp.setBounds(348, 41, 308, 156);
		panelMainArea.add(pnlDigits);

		pnlOperators pnlOperators_ = new pnlOperators();
		pnlOperators_.setBounds(372, 22, 184, 148);
		panelMainArea.add(pnlOperators_);
		//
		JButton btnResult = new JButton("=");
		btnResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcolo();
			}

			
		});
		btnResult.setBounds(420, 168, 85, 21);
		panelMainArea.add(btnResult);
		pnlOp.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnlOperators_.addOperators(model.listOperator());

		// pnlDigits.addActionListener(e -> System.out.println( e.getSource()) );
		// pnlDigits.addActionListener(e -> System.out.printf("Command: %s%n",
		// e.getActionCommand()) );
		pnlDigits.addActionListener(e -> addDigit(e.getActionCommand()));
		//
		pnlOperators_.addActionListener(e -> testCal(e.getActionCommand(), txtResult.getText()));

//		testModel();

	}

	//
	  private void testCal(String s, String op) {
	  
	  model.pushOperator(s.charAt(0));
	  model.pushOperand(Integer.parseInt(op));
	  txtResult.setText("0");
	  
	  
	  
	  
	  
	  
	  
	  }
	 

	private int testModel() {
		// funziona solo se gli operantori sono il primo elemento dello stack

		/*
		 * model.pushOperator('R');
		 * 
		 * model.pushOperand(24); model.pushOperator('+'); model.pushOperand(1);
		 */

		int result = model.eval();
		System.out.printf("\n%s = %d\n", model.dump(), result);
		return result;
	}
		private  boolean varClear = false;
	private void addDigit(String actionCommand) {
		if(varClear == true ) {
			txtResult.setText("0");
			model.removeElement();
			varClear = false;
		}
		int currentResult = Integer.parseInt(txtResult.getText());
		currentResult = currentResult * 10 + Integer.parseInt(actionCommand);
		txtResult.setText(String.format("%d", currentResult));

	}
	//
	
	
	public void calcolo() {
		model.pushOperand(Integer.parseInt(txtResult.getText()));
		model.reverse();
		txtResult.setText(testModel()+ "");
		varClear = true;
		
	}
	
	
}
