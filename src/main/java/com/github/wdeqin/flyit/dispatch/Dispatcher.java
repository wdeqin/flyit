package com.github.wdeqin.flyit.dispatch;

public interface Dispatcher<E> {
	boolean dispatch(Iterable<E> iters);
	boolean cleanUp();
}
