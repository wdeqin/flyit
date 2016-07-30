package com.github.wdeqin.flyit.world.test;

import org.apache.ibatis.session.SqlSession;

import com.github.wdeqin.flyit.util.Timer;
import com.github.wdeqin.flyit.world.util.MybatisSessionFactory;

public class RepeatEmptySession implements Runnable {

	// Unit: ms
	private long millsElapsed;

	private int repeatTime;

	public RepeatEmptySession(int repeatTime) {
		this.repeatTime = repeatTime;
	}

	@Override
	public void run() {
		if (repeatTime <= 0) {
			return;
		}

		Timer timer = new Timer();

		for (int i = 0; i < repeatTime; i++) {
			timer.start();
			SqlSession session = MybatisSessionFactory.getSqlSessionFactory().openSession();
			try {
				session.commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.rollback();
			} finally {
				session.close();
			}
			timer.end();
			millsElapsed += timer.getMillisElapsed();
		}
		
		System.out.println(String.format("Repeat: %d, Mills: %d", repeatTime, millsElapsed));
	}

	public long getMillsElapsed() {
		return millsElapsed;
	}

}
