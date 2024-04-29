package cp317project;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.text.SimpleDateFormat;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import cp317project.views.Calculator;
import cp317project.views.Notepad;
import cp317project.views.TodoListPage;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;


public class Main {	
	
	private static final String todoListFilename = "example-100000.json";
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	
	public static void main(String[] args) {
		System.out.println("Running...");
		
		Worker worker = new Worker();
		TodoListModel model = new TodoListModel(todoListFilename, worker);
		model.loadFromFile();
		
		//to-do list
		TodoListPage todoPage = new TodoListPage(model, worker);
		JFrame frame = new JFrame();
		frame.setBackground(new Color(240, 255, 255));
		todoPage.setBackground(new Color(240, 255, 255));
		frame.getContentPane().add(todoPage);
		JLabel lblTodoList = new JLabel("To-Do List");
		lblTodoList.setFont(new Font("Arial Black", Font.BOLD, 11));
		lblTodoList.setBounds(24, 12, 107, 14);
		todoPage.add(lblTodoList);
		
		//notepad 
		JLabel lblNewLabel = new JLabel("Notepad");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 11));
		lblNewLabel.setBounds(493, 11, 107, 14);
		todoPage.add(lblNewLabel);
		Notepad notepad = new Notepad();
		notepad.setBounds(493, 43, 331, 300);
		todoPage.add(notepad);

		//calculator
		Calculator calculator = new Calculator();
		calculator.setBounds(843, 43, 345, 400);
		todoPage.add(calculator);
		JLabel lblCalculator = new JLabel("Calculator");
		lblCalculator.setFont(new Font("Arial Black", Font.BOLD, 11));
		lblCalculator.setBounds(843, 13, 107, 14);
		todoPage.add(lblCalculator);
		
		//logo
		File f = new File("Widgie_Logo.png"); 
		JLabel logoLabel = new JLabel(new ImageIcon(new ImageIcon(f.getName()).getImage().getScaledInstance(112, 112, Image.SCALE_DEFAULT)));
		logoLabel.setBounds(604, 354, 112, 112);
		todoPage.add(logoLabel);
		
		
		
		
		frame.setSize(1220, 521);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				System.out.println("Closing...");
				model.saveToFile();
				try {
					worker.stop();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Worker thread closed.");
				System.exit(0);
			}
		});
		
	}
}
 
