package cp317project;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TodoListReader implements Runnable {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

	private String filename;
	private TaskListCallback callback;
	
	public TodoListReader(String filename, TaskListCallback callback) {
		this.filename = filename;
		this.callback = callback;
	}
	
	@Override
	public void run() {
		try {
			List<Task> tasks = readTaskFile(filename);
			callback.receiveTasks(tasks);
		} catch (FileNotFoundException | JSONException | ParseException e) {
			e.printStackTrace();
		}
	}

	public static List<Task> readTaskFile(String filename) throws FileNotFoundException, JSONException, ParseException {
		List<Task> tasks = new ArrayList<Task>();
		
		StringBuilder builder = new StringBuilder();
		File myfile = new File(filename);
		Scanner keyboard = new Scanner(myfile);
		while(keyboard.hasNextLine()) {
			builder.append(keyboard.nextLine());
		}
		keyboard.close();
		
		JSONObject obj = new JSONObject(builder.toString());
		JSONArray todoList = obj.getJSONArray("todos");
		for (int i = 0; i < todoList.length(); i++) {
			JSONObject taskObj = todoList.getJSONObject(i);
			String taskName = taskObj.getString("task");
			Date date = dateFormat.parse(taskObj.getString("date"));
			Task.Status status = taskObj.getEnum(Task.Status.class, "status");
			String label = taskObj.getString("label");
			Task task = new Task(taskName, date, status, label);
			tasks.add(task);
		}
		
		return tasks;
	}
}
