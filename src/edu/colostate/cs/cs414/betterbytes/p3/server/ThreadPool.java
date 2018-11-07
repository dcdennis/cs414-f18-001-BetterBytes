package edu.colostate.cs.cs414.betterbytes.p3.server;

import java.util.ArrayList;

public class ThreadPool {
	private ArrayList<WorkerThread> threadList;

	public ThreadPool(int threadCount) {
		threadList = new ArrayList<WorkerThread>();
		for (int i = 0; i < threadCount; i++)
			threadList.add(new WorkerThread(i));
		for (WorkerThread thread : threadList)
			thread.start();
	}

	public void terminate() {
		for (WorkerThread i : threadList) {
			i.terminate();
			i.interrupt();
		}
	}
}
