package com.flyit.dispatch.test;

import java.util.ArrayList;
import java.util.Random;

import com.flyit.dispatch.DefaultDispatchee;
import com.flyit.dispatch.Dispatcher;
import com.flyit.dispatch.ThresholdDispatcher;

public class DispatchMain {
	public static void main(String[] args) {
		Dispatcher<Integer> dispatcher = new ThresholdDispatcher<Integer>(new DefaultDispatchee(5), 10);
		ArrayList<Integer> iters = new ArrayList<Integer>(20);
		
		Random r = new Random(System.nanoTime());
		
		for (int j = 0; j < 20; j++) {
			iters.add(j, 0);
		}
		
		for (int i = 0; i < 3; i++) {
			for (int j  = 0; j < 20; j++) {
				iters.set(j, r.nextInt(10));
			}
			dispatcher.dispatch(iters);
		}
		
		dispatcher.cleanUp();
	}
}
