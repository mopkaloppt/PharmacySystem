package com.pharmacysystem.db.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.JLabel;

public class UserType extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserType frame = new UserType();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserType() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JRadioButton rdbtnDoctor = new JRadioButton("    Doctor");
		rdbtnDoctor.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		rdbtnDoctor.setBounds(149, 102, 141, 23);
		contentPane.add(rdbtnDoctor);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("    Pharmacist");
		rdbtnNewRadioButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		rdbtnNewRadioButton.setBounds(149, 172, 141, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("    Patient");
		rdbtnNewRadioButton_1.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		rdbtnNewRadioButton_1.setBounds(149, 137, 141, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		JLabel lblPleaseSpecifyYourself = new JLabel("Please specify yourself as one of the following:");
		lblPleaseSpecifyYourself.setBounds(81, 57, 363, 16);
		contentPane.add(lblPleaseSpecifyYourself);
		
		
	}
}
