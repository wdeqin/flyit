package com.flyit.world.test;

import org.apache.ibatis.session.SqlSession;

import com.flyit.world.dao.TestPlanMapper;
import com.flyit.world.model.TestPlan;
import com.flyit.world.util.MybatisSessionFactory;

public class TestPlanDaemon implements Runnable {
	private long scanPeriod;

	public TestPlanDaemon(long scanPeriod) {
		if (scanPeriod < 500) {
			scanPeriod = 500;
		}
		this.scanPeriod = scanPeriod;
	}

	@Override
	public void run() {
		String status = "A";
		String processedStatus = "B";
		boolean sleep = true;
		while (true) {
			SqlSession session = MybatisSessionFactory.getSqlSessionFactory().openSession();
			try {
				TestPlanMapper testPlanMapper = session.getMapper(TestPlanMapper.class);
				TestPlan testPlan = testPlanMapper.selectOneStatusForUpdate(status);

				if (testPlan != null) {
					testPlan.setStatus(processedStatus);
					testPlanMapper.updateByPrimaryKey(testPlan);
					session.commit();
					System.out.println(String.format("Plan #%d %s processed.", testPlan.getId(), testPlan.getName()));
					sleep = false;
				} else {
					sleep = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
				session.rollback();
			} finally {
				session.close();
			}
			
			if (sleep) {
				try {
					System.out.println(String.format("Test plan daemon sleep %dms...", scanPeriod));
					Thread.sleep(scanPeriod);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
