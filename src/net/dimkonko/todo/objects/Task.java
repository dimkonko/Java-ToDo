package net.dimkonko.todo.objects;

public class Task {
	
	private int id;
	private String title;
	private boolean isDone;
	
	public Task(int id, String title, boolean isDone) {
		this.id = id;
		this.title = title;
		this.isDone = isDone;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String task) {
		this.title = task;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	
	public int getIsDone() {
		if(isDone)
			return 1;
		else
			return 0;
	}
	
	public int getID() {
		return id;
	}
}
