package co.gv.phonebook.entity;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class User {

	private Integer id;
	private String name;
	private String email;
	private String password;
	private Date createdAt = new Date();

	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

}
