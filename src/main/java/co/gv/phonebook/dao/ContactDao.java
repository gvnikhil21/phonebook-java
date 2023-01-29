package co.gv.phonebook.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import co.gv.phonebook.entity.Contact;

public interface ContactDao {

	// CRUD
	
	@Insert("insert into contacts(firstname,lastname,email,phone,avatar,address,city,pincode,state,country,user_id,created_at) "
			+ "values(#{firstname},#{lastname},#{email},#{phone},#{avatar},#{address},#{city},#{pincode},#{state},#{country},#{userId},#{createdAt})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void createContact(Contact c);
	
	@Select("select * from contacts where id=#{id} and user_id=#{userId}")
	@Results({ 
		@Result(column = "user_id", property = "userId"),
		@Result(column = "created_at", property = "createdAt") 
	})
	public Contact getContact(@Param("id") Integer id,@Param("userId") Integer userId);
	
	@Update("update contacts set firstname=#{firstname},lastname=#{lastname}, email=#{email},phone=#{phone},avatar=#{avatar},"
			+ "address=#{address},city=#{city},pincode=#{pincode},state=#{state},country=#{country},"
			+ "user_id=#{userId} where id=#{id}")
	public void updateContact(Contact c);
	
	@Delete("delete from contacts where id=#{id}")
	public void deleteContact(Integer id);

	// QUERIES
	
	@Select("select * from contacts where email=#{email}")
	@Results({ 
		@Result(column = "user_id", property = "userId"),
		@Result(column = "created_at", property = "createdAt") 
	})
	public Contact getContactByEmail(String email);

	@Select("select * from contacts where phone=#{phone}")
	@Results({ 
		@Result(column = "user_id", property = "userId"),
		@Result(column = "created_at", property = "createdAt") 
	})
	public Contact getContactByPhone(String phone);
	
	@Select("select * from contacts where user_id=#{userId}")
	@Results({ 
		@Result(column = "user_id", property = "userId"),
		@Result(column = "created_at", property = "createdAt") 
	})
	public List<Contact> getAllContacts(Integer userId);

}
