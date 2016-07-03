package com.flyit.world.test;

import org.apache.ibatis.session.SqlSession;

import com.flyit.world.dao.CityMapper;
import com.flyit.world.util.MybatisSessionFactory;

public class CityLockWait implements Runnable {

	private int cityId;
	private int timeToWait;

	public CityLockWait(int cityId, int timeToWait) {
		this.cityId = cityId;
		this.timeToWait = timeToWait;
	}

	@Override
	public void run() {
		long threadId = Thread.currentThread().getId();
		System.out.println(String.format("Thread #%d start...", threadId));
		SqlSession session = MybatisSessionFactory.getSqlSessionFactory().openSession();
		System.out.println(String.format("Thread #%d session start...", threadId));

		try {
			CityMapper cityMapper = session.getMapper(CityMapper.class);

			cityMapper.selectByPrimaryKeyForUpdate(cityId);
			System.out.println(String.format("Thread #%d lock city #%d...", threadId, cityId));

			Thread.sleep(timeToWait);
			
			session.commit();
			System.out.println(String.format("Thread #%d session commit.", threadId));
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
			System.out.println(String.format("Thread #%d session rollback.", threadId));
		} finally {
			session.close();
			System.out.println(String.format("Thread #%d session close.", threadId));
		}
	}

}
