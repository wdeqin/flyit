package com.flyit.dispatch;

public interface Dispatchee<E> {
	boolean process(int sorNum, Iterable<E> iters);
	int getNumOfSor();
	int getSorNum(E e);
}
