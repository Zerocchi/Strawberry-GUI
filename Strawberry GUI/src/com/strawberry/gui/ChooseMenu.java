package com.strawberry.gui;
import java.awt.EventQueue;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JButton;

import com.strawberry.da.OperationDA;


public class ChooseMenu {

	private static ChooseMenu window;
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JCheckBox chckbxNasiDagang;
	private JCheckBox chckbxNasiAyam;
	private JCheckBox chckbxNasiArab;
	private JCheckBox chckbxTiramisu;
	private JCheckBox chckbxCocktail;
	private JLabel lblTotalPrice;
	private OperationDA operation;
	
	public void callWindow(){
		window.frame.setVisible(false);
		window.frame.dispose();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new ChooseMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChooseMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Choose Menu");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textField = new JTextField();
		textField.setText("0");
		textField.setColumns(10);
		textField.setEditable(false);
		
		textField_1 = new JTextField();
		textField_1.setText("0");
		textField_1.setColumns(10);
		textField_1.setEditable(false);
		
		textField_2 = new JTextField();
		textField_2.setText("0");
		textField_2.setColumns(10);
		textField_2.setEditable(false);
		
		textField_3 = new JTextField();
		textField_3.setText("0");
		textField_3.setColumns(10);
		textField_3.setEditable(false);
		
		textField_4 = new JTextField();
		textField_4.setText("0");
		textField_4.setColumns(10);
		textField_4.setEditable(false);
		
		textField_5 = new JTextField();
		textField_5.setText("0.00");
		textField_5.setColumns(10);
		
		
		chckbxNasiDagang = new JCheckBox("Nasi Dagang");
		chckbxNasiDagang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		chckbxNasiDagang.addActionListener(new ActionListener(){
			 
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(chckbxNasiDagang.isSelected()){
					textField.setEditable(true);
				} else {
					textField.setEditable(false);
					textField.setText("0");
				}
				
			}
			
		});
		
		chckbxNasiAyam = new JCheckBox("Nasi Ayam");
		chckbxNasiAyam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		chckbxNasiAyam.addActionListener(new ActionListener(){
			 
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(chckbxNasiAyam.isSelected()){
					textField_1.setEditable(true);
				} else {
					textField_1.setEditable(false);
					textField_1.setText("0");
				}
				
			}
			
		});
		
		chckbxNasiArab = new JCheckBox("Nasi Arab");
		chckbxNasiArab.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		chckbxNasiArab.addActionListener(new ActionListener(){
			 
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(chckbxNasiArab.isSelected()){
					textField_2.setEditable(true);
				} else {
					textField_2.setEditable(false);
					textField_2.setText("0");
				}
				
			}
			
		});
		
		chckbxTiramisu = new JCheckBox("Tiramisu");
		chckbxTiramisu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		chckbxTiramisu.addActionListener(new ActionListener(){
			 
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(chckbxTiramisu.isSelected()){
					textField_3.setEditable(true);
				} else {
					textField_3.setEditable(false);
					textField_3.setText("0");
				}
				
			}
			
		});
		
		chckbxCocktail = new JCheckBox("Cocktail");
		chckbxCocktail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		chckbxCocktail.addActionListener(new ActionListener(){
			 
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(chckbxCocktail.isSelected()){
					textField_4.setEditable(true);
				} else {
					textField_4.setEditable(false);
					textField_4.setText("0");
				}
				
			}
			
		});
		
		lblTotalPrice = new JLabel("Total Price:");
		lblTotalPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnCalcPrice = new JButton("Calculate price");
		btnCalcPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		btnCalcPrice.addActionListener(new ActionListener(){
			 
			@Override
			public void actionPerformed(ActionEvent e) {
				String price = String.format("%.2f",calculateTotalPrice());
				textField_5.setText(price);
			}
			
		});
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		btnSubmit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				operation = new OperationDA();
				double price = calculateTotalPrice();
				int lastOrderId = operation.getLastOrderId();
				operation.setTotalPrice(lastOrderId, price);
				callWindow();
			}
			
		});
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(91)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblTotalPrice)
								.addPreferredGap(ComponentPlacement.RELATED))
							.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(chckbxNasiDagang)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(chckbxNasiAyam)
									.addComponent(chckbxNasiArab)
									.addComponent(chckbxTiramisu)
									.addComponent(chckbxCocktail)
									.addComponent(btnCalcPrice))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(18)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(18)
										.addComponent(btnSubmit))))))
					.addContainerGap(150, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxNasiDagang)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxNasiAyam)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxNasiArab)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxTiramisu)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxCocktail)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTotalPrice))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCalcPrice)
						.addComponent(btnSubmit))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
		
		String price = String.format("%.2f",calculateTotalPrice());
		textField_5.setText(price);
	}
	
		private double calculateTotalPrice(){
		
		double NasiDagangQtt = Integer.parseInt(textField.getText());
		double NasiAyamQtt = Integer.parseInt(textField_1.getText());
		double NasiArabQtt = Integer.parseInt(textField_2.getText());
		double TiramisuQtt = Integer.parseInt(textField_3.getText());
		double CocktailQtt = Integer.parseInt(textField_4.getText());
		
		double totPrice = 0;
		
		double ndPrice = NasiDagangQtt * 5.00;
		double naPrice = NasiAyamQtt * 4.50;
		double narPrice = NasiArabQtt * 5.00;
		double tiraPrice = TiramisuQtt * 6.00;
		double ckPrice = CocktailQtt * 3.00;
		
		totPrice = ndPrice + naPrice + narPrice + tiraPrice + ckPrice;
		
		return totPrice;
	}
}