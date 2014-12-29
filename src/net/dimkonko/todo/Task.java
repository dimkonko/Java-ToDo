package net.dimkonko.todo;

public class Task {
	
	private String title;
	private boolean isDone;
	
	public Task(String title, boolean isDone) {
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
}
