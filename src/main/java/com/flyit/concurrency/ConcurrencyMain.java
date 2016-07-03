package com.flyit.concurrency;

public class ConcurrencyMain {
	public static void main(String[] args) {
		for (int i = 1; i <= 10; ++i) {
			Thread t = new Thread(new HelloRunnable(String.format("#%d", i)));
			t.start();
		}
	}
}
