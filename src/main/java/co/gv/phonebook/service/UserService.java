package co.gv.phonebook.service;

import java.io.IOException;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;

import co.gv.phonebook.dao.DaoFactory;
import co.gv.phonebook.dao.UserDao;
import co.gv.phonebook.entity.User;

public class UserService {

	public User registerUser(String name, String email, String password, String cPassword, Map<String, String> errors)
			throws IOException {
		if (name.trim().length() == 0) {
			errors.put("name", "Name is required");
		} else if (name.trim().length() < 3) {
			errors.put("name", "Atleast 3 letters are required");
		}
		if (email.trim().length() == 0) {
			errors.put("email", "Email is required");
		}
		if (password.trim().length() == 0) {
			errors.put("password", "Password is required");
		}
		if (password.equals(cPassword) == false) {
			errors.put("cPassword", "Passwords do not match");
		}

		UserDao dao = DaoFactory.getUserDao();
		User user = dao.getUserByEmail(email);

		if (user != null) {
			errors.put("email", "This email is already registered with us");
		}

		if (errors.size() > 0) {
			return null;
		}

		password = BCrypt.hashpw(password, BCrypt.gensalt(12));
		user = new User(name, email, password);
		dao.createNewUser(user);

		return user;
	}

	public User login(String email, String password, Map<String, String> errors) throws IOException {
		if (email.trim().length() == 0) {
			errors.put("email", "Email is required");
		}
		if (password.trim().length() == 0) {
			errors.put("password", "Password is required");
		}

		if (errors.size() > 0) {
			return null;
		}

		UserDao dao = DaoFactory.getUserDao();
		User user = dao.getUserByEmail(email);

		if (user == null) {
			errors.put("email", "Invalid email/password");
		} else {
			boolean result = BCrypt.checkpw(password, user.getPassword());
			if (result == false) {
				errors.put("email", "Invalid email/password");
			}
		}

		if (errors.size() > 0) {
			return null;
		}

		return user;
	}

}
