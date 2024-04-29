package cp317project;

import java.util.Date;


public class Task extends Object {
	public enum Status {
		NotStarted,
		InProgress,
		Completed
	}
	
	private String taskName;
	private Date date;
	private Status status;
	private String label;
	
	public static final String[] displayNames = {
			"Task",
			"Date",
			"Status",
			"Label"
	};
	
	public static final Class[] classTypes = {String.class, Date.class, Status.class, String.class};
	
	public static Task createEmptyTask() {
		return new Task("", new Date(), Status.NotStarted, "");
	}

	public Task(String taskName, Date date, Status status, String label) {		
		this.taskName = taskName;
		this.date = date;
		this.status = status;
		this.label = label;		
	}

	public String getTaskName() {
		return taskName;
	}
	
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}

	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public Object getValueAt(int index) {
		switch (index) {
		case 0:
			return getTaskName();
		case 1:
			return getDate();
		case 2:
			return getStatus();
		case 3:
			return getLabel();
		default:
			throw new RuntimeException("Invalid column index");
		}
	}
	
	public void setValueAt(Object value, int index) {
		switch (index) {
		case 0:
			setTaskName((String) value);
			break;
		case 1:
			setDate((Date) value);
			break;
		case 2:
			setStatus(Status.valueOf((String) value));
			break;
		case 3:
			setLabel((String) value);
			break;
		default:
			throw new RuntimeException("Invalid column index");
		}
	}
	
	public String toString() {
		return String.format("%s | %s | %s | %s", taskName, date.toString(), status, label);
	}
}
