package co.gv.phonebook.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import co.gv.phonebook.entity.User;

public interface UserDao {

	// CRUD operations
	@Insert("insert into users(name, email, password, created_at) values(#{name}, #{email}, #{password}, #{createdAt})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void createNewUser(User user);

	// QUERIES
	@Select("select * from users where email=#{email}")
	@Results({ @Result(column = "created_at", property = "createdAt") })
	public User getUserByEmail(String email);
}
