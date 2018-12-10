package edu.colostate.cs.cs414.betterbytes.p4.server;

import java.util.ArrayList;

/**
 * ThreadPool object. Maintains and terminates a pool of worker threads
 */
public class ThreadPool {
	private ArrayList<WorkerThread> threadList;

	/**
	 * ThreadPool constructor. Initialized a new ThreadPool of WorkerThreads and start them.
	 * @param threadCount Number of threads to run
	 */
	public ThreadPool(int threadCount) {
		threadList = new ArrayList<WorkerThread>();
		for (int i = 0; i < threadCount; i++)
			threadList.add(new WorkerThread(i));
		for (WorkerThread thread : threadList)
			thread.start();
	}

	/**
	 * Terminate all active WorkerThreads in the ThreadPool
	 */
	public void terminate() {
		for (WorkerThread i : threadList) {
			i.terminate();
			i.interrupt();
		}
	}
}
