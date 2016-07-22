package com.flyit.dispatch;

public class DefaultDispatchee implements Dispatchee<Integer> {
	
	protected int numOfSor;
	
	public DefaultDispatchee(int numOfSor) {
		assert(numOfSor > 0);
		this.numOfSor = numOfSor;
	}

	@Override
	public boolean process(int sorNum, Iterable<Integer> iters) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				StringBuilder strBuilder = new StringBuilder();
				strBuilder.append(String.format("#%d $%d [", Thread.currentThread().getId(), sorNum));
				for (Integer e: iters) {
					strBuilder.append(e);
					strBuilder.append(", ");
				}
				strBuilder.delete(strBuilder.length() - 2, strBuilder.length());
				strBuilder.append("]");
				System.out.println(strBuilder.toString());
			}
			
		}).start();
		return false;
	}

	@Override
	public int getNumOfSor() {
		return numOfSor;
	}

	@Override
	public int getSorNum(Integer e) {
		return (e % numOfSor);
	}

}
