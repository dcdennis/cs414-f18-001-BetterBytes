package edu.colostate.cs.cs414.betterbytes.p4.server;

import java.nio.channels.SelectionKey;
import java.util.LinkedList;
import java.util.List;

//Simple Blocking Queue implementation for tasks
public class TaskQueue {
	private List<SelectionKey> queue = new LinkedList<SelectionKey>();

	public TaskQueue() {
	};

	public synchronized void enqueue(SelectionKey t) throws InterruptedException {
		int size = queue.size();
		queue.add(t);
		if (size == 0) {
			notifyAll();
		}

	}

	public synchronized SelectionKey dequeue() throws InterruptedException {
		while (queue.size() == 0) {
			wait();
		}
		return queue.remove(0);
	}

}
