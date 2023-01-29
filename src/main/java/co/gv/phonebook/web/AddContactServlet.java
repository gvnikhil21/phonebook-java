package co.gv.phonebook.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.gv.phonebook.entity.Contact;
import co.gv.phonebook.entity.User;
import co.gv.phonebook.service.ContactService;

@WebServlet("/add-contact")
public class AddContactServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("title", "Add New Contact Details");
		req.getRequestDispatcher("/WEB-INF/views/contactForm.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Contact c = (Contact) req.getAttribute("contact");
		User user = (User) req.getSession().getAttribute("user");
		c.setUserId(user.getId());

		ContactService service = new ContactService();
		Map<String, String> errors = new HashMap<String, String>();
		c = service.addNewContact(c, errors);

		if (errors.size() > 0) {
			req.setAttribute("errors", errors);
			req.setAttribute("contact", c);
			req.setAttribute("title", "Add New Contact Details");
			req.getRequestDispatcher("/WEB-INF/views/contactForm.jsp").forward(req, resp);
		} else {
			resp.sendRedirect("./view-contact-details?id=" + c.getId());
		}
	}

}
