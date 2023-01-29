package co.gv.phonebook.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import co.gv.phonebook.dao.ContactDao;
import co.gv.phonebook.dao.DaoFactory;
import co.gv.phonebook.entity.Contact;

public class ContactService {

	public Contact addNewContact(Contact c, Map<String, String> errors) throws IOException {

		String firstname = c.getFirstname().trim();

		if (firstname.length() == 0) {
			errors.put("firstname", "first name is required");
		} else if (firstname.length() < 3) {
			errors.put("firstname", "at least 3 letters required for first name");
		}
		if (c.getEmail().trim().length() == 0) {
			errors.put("email", "email address required");
		}
		if (c.getPhone().trim().length() == 0) {
			errors.put("phone", "phone number is required");
		}

		if (errors.size() > 0) {
			return c;
		}

		ContactDao dao = DaoFactory.getContactDao();
		Contact c1 = dao.getContactByEmail(c.getEmail());
		if (c1 != null) {
			errors.put("email", "there's already a contact for this email");
		}

		c1 = dao.getContactByPhone(c.getPhone());
		if (c1 != null) {
			errors.put("phone", "there's already a contact for this phone");
		}

		if (errors.size() > 0) {
			return c;
		}

		dao.createContact(c);
		return c;
	}

	public void updateContact(Integer id, Contact c, Map<String, String> errors) throws IOException {
		c.setId(id);

		String firstname = c.getFirstname().trim();

		if (firstname.length() == 0) {
			errors.put("firstname", "first name is required");
		} else if (firstname.length() < 3) {
			errors.put("firstname", "at least 3 letters required for first name");
		}
		if (c.getEmail().trim().length() == 0) {
			errors.put("email", "email address required");
		}
		if (c.getPhone().trim().length() == 0) {
			errors.put("phone", "phone number is required");
		}

		if (errors.size() > 0) {
			return;
		}

		ContactDao dao = DaoFactory.getContactDao();
		dao.updateContact(c);
	}

	public void deleteContactById(Integer id, Integer userId, Map<String, String> errors) throws IOException {
		ContactDao dao = DaoFactory.getContactDao();
		Contact c1 = dao.getContact(id, userId);
		if (c1 == null) {
			errors.put("userId", "permission denied for deleting for this contact");
			return;
		}
		dao.deleteContact(id);
	}

	public Contact getContactById(Integer id, Integer userId) throws IOException {
		return DaoFactory.getContactDao().getContact(id, userId);
	}

	public List<Contact> getAllContacts(Integer userId) throws IOException {
		return DaoFactory.getContactDao().getAllContacts(userId);
	}

}
