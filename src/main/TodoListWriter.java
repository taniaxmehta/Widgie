package cp317project;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class TodoListWriter implements Runnable {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	
	private String filename;
	private List<Task> tasks;
	
	public TodoListWriter(String filename, List<Task> tasks) {
		this.filename = filename;
		this.tasks = tasks;
	}

	@Override
	public void run() {
		try {
			writeTasksToFile(this.filename, this.tasks);			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeTasksToFile(String filename, List<Task> tasks) throws IOException {
		JSONArray todoList = new JSONArray();
		for (Task task : tasks) {
			JSONObject taskObj = new JSONObject();
			taskObj.put("task", task.getTaskName());
			taskObj.put("date", dateFormat.format(task.getDate()));
			taskObj.put("status", task.getStatus());
			taskObj.put("label", task.getLabel());
			todoList.put(taskObj);
		}
		JSONObject obj = new JSONObject();
		obj.put("todos", todoList);
		
		FileWriter writer = new FileWriter(filename);
		writer.write(obj.toString());
		writer.close();
	}

}
