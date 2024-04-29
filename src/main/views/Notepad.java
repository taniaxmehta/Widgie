package cp317project.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;

public class Notepad extends JPanel implements ActionListener {
	
	private TextArea textArea = new TextArea("", 0,0, TextArea.SCROLLBARS_VERTICAL_ONLY);

	JButton btnNewButton = new JButton("Open");
	JButton btnNewButton_1 = new JButton("Save");
	JButton btnNewButton_2 = new JButton("New Note");
	
	public Notepad() {
		
		//layout and components
		this.setSize(331, 300); 
		textArea.setBackground(new Color(255, 255, 240));
		textArea.setBounds(10, 40, 311, 250);
		this.textArea.setFont(new Font("Arial", Font.BOLD, 12)); 
		
		setLayout(null);
		add(textArea);
		
		
		btnNewButton.setBounds(10, 11, 89, 23);
		btnNewButton.addActionListener(this);
		add(btnNewButton);
		
	
		btnNewButton_1.setBounds(109, 11, 89, 23);
		btnNewButton_1.addActionListener(this);
		add(btnNewButton_1);
		
		
		btnNewButton_2.setBounds(208, 11, 89, 23);
		btnNewButton_2.addActionListener(this);
		add(btnNewButton_2);

		
	}
	
	public void actionPerformed (ActionEvent e) {
		
		//open
		if (e.getSource() == btnNewButton) {
			File demofolder = new File("C:\\Users\\melis\\Desktop\\Second Desktop\\CP317 Test Files"); //change to your demo folder otherwise leave empty
			JFileChooser open = new JFileChooser(demofolder); 
			int option = open.showOpenDialog(this); 
			
			if (option == JFileChooser.APPROVE_OPTION) {
				try {
					
					Scanner scan = new Scanner(new FileReader(open.getSelectedFile().getPath()));
					while (scan.hasNext()) 
						this.textArea.append(scan.nextLine() + "\n"); 
				} catch (Exception ex) { 
					
					System.out.println(ex.getMessage());
				}
			}
		}
		
		//save
		else if (e.getSource() == btnNewButton_1) {
			File demofolder = new File("C:\\Users\\melis\\Desktop\\Second Desktop\\CP317 Test Files"); //change to your demo folder otherwise leave empty
			JFileChooser save = new JFileChooser(demofolder); 
			int option = save.showSaveDialog(this); 
			
			if (option == JFileChooser.APPROVE_OPTION) {
				try {
				
					BufferedWriter out = new BufferedWriter(new FileWriter(save.getSelectedFile().getPath()));
					out.write(this.textArea.getText()); 
					out.close();
				} catch (Exception ex) { 
					
					System.out.println(ex.getMessage());
				}
			}
		}
		
		else if (e.getSource() == btnNewButton_2) {
			this.textArea.setText(""); 
			
		}
	}
}