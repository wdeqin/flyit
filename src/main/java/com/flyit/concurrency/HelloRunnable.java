package com.flyit.concurrency;

public class HelloRunnable implements Runnable {
	private String name;
	
	public HelloRunnable(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Hello from " + this.name + "!");
	}

}
