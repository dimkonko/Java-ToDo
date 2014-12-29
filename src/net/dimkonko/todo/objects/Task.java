package net.dimkonko.todo.objects;

public class Task {
	
	private int id;
	private String title;
	private byte isDone;
	
	public Task(int id, String title, byte isDone) {
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

	public short isDone() {
		return isDone;
	}

	public void setDone(byte isDone) {
		this.isDone = isDone;
	}
	
	public int getIsDone() {
		if(isDone > 0)
			return 1;
		else
			return 0;
	}
	
	public int getID() {
		return id;
	}
}
