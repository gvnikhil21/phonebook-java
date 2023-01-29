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

@WebServlet("/edit-and-update-contact")
public class EditUpdateContactServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String input = req.getParameter("id");
		if (input == null) {
			resp.sendRedirect("./");
			return;
		}

		Integer id = new Integer(input);
		ContactService service = new ContactService();
		User user = (User) req.getSession().getAttribute("user");

		Contact contact = service.getContactById(id, user.getId());

		req.setAttribute("contact", contact);
		req.setAttribute("title", "Edit Contact Details");
		req.getRequestDispatcher("/WEB-INF/views/contactForm.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String input = req.getParameter("id");
		if (input == null) {
			resp.sendRedirect("./");
			return;
		}

		Integer id = new Integer(input);
		Contact c = (Contact) req.getAttribute("contact");
		User user = (User) req.getSession().getAttribute("user");
		c.setUserId(user.getId());

		ContactService service = new ContactService();
		Map<String, String> errors = new HashMap<String, String>();
		service.updateContact(id, c, errors);

		if (errors.size() > 0) {
			req.setAttribute("errors", errors);
			req.setAttribute("contact", c);
			req.setAttribute("title", "Edit Contact Details");
			req.getRequestDispatcher("/WEB-INF/views/contactForm.jsp").forward(req, resp);
		} else {
			resp.sendRedirect("./view-contact-details?id=" + c.getId());
		}
	}

}
