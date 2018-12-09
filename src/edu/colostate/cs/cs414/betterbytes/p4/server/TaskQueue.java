package edu.colostate.cs.cs414.betterbytes.p4.server;

import java.nio.channels.SelectionKey;
import java.util.LinkedList;
import java.util.List;

//Simple Blocking Queue implementation for tasks

/**
 * Simple Blocking Queue Implementation for tasks
 * @version 1.0
 */
public class TaskQueue {
	private List<SelectionKey> queue = new LinkedList<SelectionKey>();

	public TaskQueue() {
	};

	/**
	 * Enqueue a SelectionKey. If the queue is empty, make a call to notifyAll
	 * @param t SelectionKey
	 * @throws InterruptedException
	 * @see java.lang.Object#notifyAll()
	 */
	public synchronized void enqueue(SelectionKey t) throws InterruptedException {
		int size = queue.size();
		queue.add(t);
		if (size == 0) {
			notifyAll();
		}

	}

	/**
	 * Attempt to dequeue a SelectionKey. Blocks while the queue is empty.
	 * @return Dequeue selection key
	 * @throws InterruptedException
	 * @see TaskQueue#enqueue(SelectionKey)
	 */
	public synchronized SelectionKey dequeue() throws InterruptedException {
		while (queue.size() == 0) {
			wait();
		}
		return queue.remove(0);
	}

}
