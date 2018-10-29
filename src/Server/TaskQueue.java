package Server;

import java.util.LinkedList;
import java.util.List;

//Simple Blocking Queue implementation for tasks
public class TaskQueue {
	private List<Task> queue = new LinkedList<Task>();
	
	public TaskQueue(){};
	
	public synchronized void enqueue(Task t) throws InterruptedException
	{
		int size = queue.size();
		queue.add(t);
		if(size == 0) 
		{
			notifyAll();
		}
		
	}
	
	public synchronized Task dequeue() throws InterruptedException
	{
		while(queue.size() == 0)
		{
		      wait();
		}
		return queue.remove(0);
	}
	
}
