package com.flyit.dispatch;

public interface Dispatcher<E> {
	boolean dispatch(Iterable<E> iters);
	boolean cleanUp();
}
