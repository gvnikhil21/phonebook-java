package co.gv.phonebook.entity;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Contact {

	private Integer id;
	private String firstname;
	private String lastname;
	private String email;
	private String phone;
	private String avatar;
	private String address;
	private String city;
	private String pincode;
	private String state;
	private String country;
	private int userId;
	private Date createdAt = new Date();
}
