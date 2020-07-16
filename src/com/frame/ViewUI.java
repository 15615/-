package com.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ViewUI extends JFrame {

	private JPanel contentPane;

	public ViewUI(String str) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 592, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(52, 52, 490, 339);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea(str);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		this.setLocationRelativeTo(null);
	}

}
