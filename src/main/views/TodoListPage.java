package cp317project.views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.DefaultCellEditor;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;




import cp317project.Task;
import cp317project.Task.Status;

import cp317project.TodoListModel;

import cp317project.Worker;


public class TodoListPage extends JPanel {
	
	public TodoListPage(TodoListModel model, Worker worker) {
		JTable table = new JTable(model);
		table.setAutoCreateRowSorter(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(25, 41, 452, 402);
		
		JButton addEventButton = new JButton("+");
		addEventButton.setBounds(156, 454, 41, 23);
		addEventButton.addActionListener((ActionListener) new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				model.addTask(Task.createEmptyTask());
			}
			
		});
		
		JButton removeEventButton = new JButton("Remove");
		removeEventButton.setBounds(206, 454, 97, 23);
		removeEventButton.addActionListener((ActionListener) new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {	
				int row = table.getSelectedRow();
				if (row > -1) {
					row = table.convertRowIndexToModel(row);
					Date d = (Date) model.getValueAt(row, 1);
					System.out.printf("%s ", d.toString());
					model.removeTask(row);
				}
			}		
			});

		
		// Setup custom renderer for status field
		TableColumn column = table.getColumnModel().getColumn(2);
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem(Status.NotStarted.toString());
		comboBox.addItem(Status.InProgress.toString());
		comboBox.addItem(Status.Completed.toString());
		column.setCellEditor(new DefaultCellEditor(comboBox));

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Click for options");
		column.setCellRenderer(renderer);
		setLayout(null);
		
		this.add(scrollPane);
		this.add(addEventButton);
		this.add(removeEventButton);
		this.setVisible(true);
	}
}
