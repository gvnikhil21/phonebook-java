package co.gv.phonebook.dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public final class DaoFactory {
	private DaoFactory() {
	}

	public static UserDao getUserDao() throws IOException {
		return createSession().getMapper(UserDao.class);
	}

	public static ContactDao getContactDao() throws IOException {
		return createSession().getMapper(ContactDao.class);
	}

	private static SqlSession createSession() throws IOException {
		InputStream is = Resources.getResourceAsStream("myBatis-config.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession(true);
		return session;
	}
}
