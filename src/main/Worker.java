package cp317project;

import java.util.LinkedList;
import java.util.Queue;

public class Worker implements Runnable {
	
	private Thread t;
	private boolean isRunning = true;
	private Queue<Runnable> workQueue = new LinkedList<Runnable>();
	
	public Worker() {
		t = new Thread(this);
		t.start();
	}
	
	public synchronized void addWorkToQueue(Runnable work) {
		System.out.println("Adding task to queue");
		workQueue.add(work);
		notify();
	}
	
	private synchronized Runnable consumeFromQueue()  {
		if (workQueue.isEmpty()) {
			System.out.println("Queue is empty. Waiting for task...");
			try {
				wait(); //if empty, then wait for a notify
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Dequing task");
		return workQueue.poll();
	}

	public void run() {
		while (isRunning) {
			Runnable workItem = consumeFromQueue();
			if (workItem != null) {
				System.out.println("Running task...");
				workItem.run();
				System.out.println("Task complete!");
			}	
		}
	}
	
	public synchronized void stop() throws InterruptedException {
		isRunning = false;
		notify();
	}
}
