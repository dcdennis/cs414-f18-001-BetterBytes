package edu.colostate.cs.cs414.betterbytes.p4.server;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

/**
 * ThreadPoolManager class. Controls the singleton instance of itself
 */
public class ThreadPoolManager {

	private ThreadPool threadPool;
	private TaskQueue taskQueue;
	private Selector selector;

	private static final ThreadPoolManager instance = new ThreadPoolManager();

	/**
	 * Get the singleton instance object
	 * @return ThreadPoolManager instance
	 */
	public static ThreadPoolManager getInstance() {
		return instance;
	}

	/**
	 * Set up the ThreadPool based on the threadCount
	 * @param threadCount Number of threads in the pool
	 */
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

	/**
	 * Return the selector object of the ThreadPoolManager.
	 * @return selector
	 */
	public synchronized Selector selector() {
		return selector;
	}

	// Should be thread safe as TaskQueue is thread safe itself
	/**
	 * Add a new task to the taskQueue
	 * @param newTask SelectionKey
	 */
	public void addTask(SelectionKey newTask) {
		try {
			taskQueue.enqueue(newTask);
		} catch (InterruptedException e) {

		}
	}

	// Same as addTask, should not need any additional synchronization, plus if it
	// was I think it would block getting the key for the manager

	/**
	 * Get the next task from the TaskQueue. TaskQueue.dequeue is a blocking call.
	 * @return The dequeue task on success, null on Interrupt
	 * @see TaskQueue#dequeue()
	 */
	public SelectionKey nextTask() {
		try {
			return taskQueue.dequeue();
		} catch (InterruptedException e) {

		}
		return null;
	}

	/**
	 * Terminates the ThreadPool
	 */
	public void terminateThreadPool() {
		threadPool.terminate();
	}
}
