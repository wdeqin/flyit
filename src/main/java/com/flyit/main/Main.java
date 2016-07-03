package com.flyit.main;

import com.flyit.util.Timer;
import com.flyit.world.test.CityLockWait;
import com.flyit.world.test.RepeatEmptySession;
import com.flyit.world.test.TestPlanDaemon;

public class Main {
	public static void main(String[] args) {
		testPlanDaemon();
	}
	
	@SuppressWarnings("unused")
	private static void repeatEmptySession() {
		int repeatTime = 100;
		Timer t = new Timer();
		t.start();
		RepeatEmptySession r = new RepeatEmptySession(repeatTime);
		r.run();
		t.end();
		
		long millsElapsed = t.getMillisElapsed();
		System.out.println(String.format("Repeat: %d, Mills: %d", repeatTime, millsElapsed));
	}
	
	@SuppressWarnings("unused")
	private static void lockSession() {
		for (int i = 10; i > 0; i--) {
			Thread t = new Thread(new CityLockWait(1896 + i, 20));
			t.start();
		}
	}
	
	private static void testPlanDaemon() {
		long scanPeriod = 2000;
		TestPlanDaemon daemon = new TestPlanDaemon(scanPeriod);
		Thread daemonThread = new Thread(daemon);
		daemonThread.start();
	}
}
