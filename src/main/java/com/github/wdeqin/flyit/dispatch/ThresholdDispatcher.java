package com.github.wdeqin.flyit.dispatch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ThresholdDispatcher<E> implements Dispatcher<E> {
	
	protected Dispatchee<E> dispatchee;
	protected List<LinkedList<E>> queues;
	protected int numOfSor;
	protected int threshold;
	
	public ThresholdDispatcher(Dispatchee<E> dispatchee, int threshold) {
		assert(dispatchee != null);
		assert(threshold > 0);
		
		this.dispatchee = dispatchee;
		this.numOfSor = dispatchee.getNumOfSor();
		
		this.threshold = threshold;
		this.queues = new ArrayList<LinkedList<E>>(numOfSor);
		for (int i = 0; i < numOfSor; i++) {
			queues.add(i, new LinkedList<E>());
		}
	}

	@Override
	public boolean dispatch(Iterable<E> iters) {
		int sorNum;
		LinkedList<E> queue;
		
		for (E e : iters) {
			sorNum = dispatchee.getSorNum(e);
			assert(0 <= sorNum && sorNum < numOfSor);
			
			queue = queues.get(sorNum);
			queue.add(e);
			if (queue.size() >= threshold) {
				ArrayList<E> processList = new ArrayList<E>(threshold);
				for (int i = 0; i < threshold; i++) {
					processList.add(i, queue.poll());				}
				dispatchee.process(sorNum, processList);
			}
			
		}
		
		return true;
	}

	@Override
	public boolean cleanUp() {
		LinkedList<E> queue;
		for (int i = 0; i < numOfSor; i++) {
			queue = queues.get(i);
			if (queue.size() > 0) {
				dispatchee.process(i, queue);
			}
		}
		return true;
	}
	
	
}
