package com.strawberry.gui;

import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.strawberry.da.OperationDA;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginUI {

	private JFrame frame;
	private JTextField username;
	private JPasswordField password;
	private JButton login;
	private OperationDA access;
	private static LoginUI window;
	
	// method to call another window;
	public void callWindow(){
		window.frame.setVisible(false);
		window.frame.dispose();
		String[] args = {};
		try {
			com.strawberry.gui.MainUI.main(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new LoginUI();
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
	public LoginUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//Create the frame.
		frame = new JFrame("Admin Login");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		username = new JTextField();
		username.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		password = new JPasswordField();
		password.setColumns(10);
		
		login = new JButton("Login");
		login.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		login.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				access = new OperationDA();

				String u = username.getText();
				char[] p = password.getPassword();
				
				boolean acc = access.login(u, p);
				
				if(acc){
					javax.swing.SwingUtilities.invokeLater(new Runnable() {
	                	
	                    public void run() {
	                        callWindow();
	                    }
	                });
				} else {
					JOptionPane.showMessageDialog(null, "Incorrect credentials");
				}
				
			}
			
		});
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(107)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPassword)
						.addComponent(lblUsername))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(login)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(password)
							.addComponent(username, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)))
					.addContainerGap(117, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(100)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername)
						.addComponent(username, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(login)
					.addContainerGap(77, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);

	}
}