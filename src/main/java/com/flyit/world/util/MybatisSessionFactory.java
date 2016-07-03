package com.flyit.world.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisSessionFactory {
	private static SqlSessionFactory sqlSessionFactory;
	
	private MybatisSessionFactory () {}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return MybatisSessionFactory.sqlSessionFactory;
	}
	
	static {
		String resource = "mybatis/WorldConfig.xml";
        Reader reader;
		try {
			reader = Resources.getResourceAsReader(resource);
	        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
	        sqlSessionFactory = builder.build(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
