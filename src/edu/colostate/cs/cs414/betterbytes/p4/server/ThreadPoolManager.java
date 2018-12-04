package edu.colostate.cs.cs414.betterbytes.p4.server;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

public class ThreadPoolManager {

	private ThreadPool threadPool;
	private TaskQueue taskQueue;
	private Selector selector;

	private static final ThreadPoolManager instance = new ThreadPoolManager();

	public static ThreadPoolManager getInstance() {
		return instance;
	}

	public synchronized void initialize(int threadCount) {
		taskQueue = new TaskQueue();
		threadPool = new ThreadPool(threadCount);
		try {
			selector = Selector.open();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public synchronized Selector selector() {
		return selector;
	}

	// Should be thread safe as TaskQueue is thread safe itself
	public void addTask(SelectionKey newTask) {
		try {
			taskQueue.enqueue(newTask);
		} catch (InterruptedException e) {

		}
	}

	// Same as addTask, should not need any additional synchronization, plus if it
	// was I think it would block getting the key for the manager
	public SelectionKey nextTask() {
		try {
			return taskQueue.dequeue();
		} catch (InterruptedException e) {

		}
		return null;
	}

	public void terminateThreadPool() {
		threadPool.terminate();
	}
}
