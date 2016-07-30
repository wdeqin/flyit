package com.github.wdeqin.flyit.util;

public class Timer {
	private long start;
	private long end;
	
	public void start() {
		this.start = System.currentTimeMillis();
	}
	
	public void end() {
		this.end = System.currentTimeMillis();
	}
	
	public long getMillisElapsed() {
		return end - start;
	}
}
