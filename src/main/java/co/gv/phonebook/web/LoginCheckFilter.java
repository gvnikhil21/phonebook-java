package co.gv.phonebook.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.gv.phonebook.entity.User;

@WebFilter(urlPatterns = { "/dashboard", "/add-contact", "/view-contact-details", "/view-all-contacts",
		"/edit-and-update-contact", "/delete-contact" })
public class LoginCheckFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("./login");
			return;
		}

		chain.doFilter(request, response);
	}

}
