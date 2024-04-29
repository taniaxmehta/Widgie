package cp317project;


import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;



public class TodoListModel extends AbstractTableModel {
	private List<Task> tasks = new ArrayList<Task>();
	private String filename;
	private Worker worker;
	
	TodoListModel(String filename, Worker worker) {
		this.filename = filename;
		this.worker = worker;
	}
		
	public void addTask(Task task) {
		tasks.add(task);
		this.fireTableDataChanged();
		onUpdate();
	}
	
	public void removeTask(int row) {
		tasks.remove(row);
		this.fireTableRowsDeleted(row,row);
		onUpdate();
	}
	
	public void addTasks(List<Task> tasks) {
		int firstRow = this.tasks.size();
		this.tasks.addAll(tasks);
		int lastRow = this.tasks.size()-1;
		this.fireTableRowsInserted(firstRow, lastRow);
		onUpdate();
	}
	
	public List<Task> getTasks() {
		return tasks;
	}
	
	public void loadFromFile() {
		TaskListCallback cb = new TaskListCallback() {
			@Override
			public void receiveTasks(List<Task> tasks) {
				addTasks(tasks);
			}
		};
		
		TodoListReader reader = new TodoListReader(filename, cb);
		worker.addWorkToQueue(reader);
	}
	
	public void saveToFile() {
		TodoListWriter writer = new TodoListWriter(filename, tasks);
		worker.addWorkToQueue(writer);
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return Task.displayNames[columnIndex];
	}
	
	@Override
	public int getColumnCount() {
		return Task.displayNames.length;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// All cells are editable
        return true;
    }

	@Override
	public int getRowCount() {
		return tasks.size();
	}
	
	@Override
	public Class getColumnClass(int columnIndex) {
		return Task.classTypes[columnIndex];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Task task = tasks.get(rowIndex);
		return task.getValueAt(columnIndex);
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Task task = tasks.get(rowIndex);
		task.setValueAt(aValue, columnIndex);
		tasks.set(rowIndex, task);
		fireTableCellUpdated(rowIndex, columnIndex);
		onUpdate();
	}
	private void onUpdate() {
		saveToFile(); //auto-save each time there is a change
	}
	
}
