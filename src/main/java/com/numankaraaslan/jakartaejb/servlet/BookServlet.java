package com.numankaraaslan.jakartaejb.servlet;

import java.io.IOException;

import com.numankaraaslan.jakartaejb.model.Book;
import com.numankaraaslan.jakartaejb.repo.BookRepo;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Stateless
// Everything is jakarta.servlet package, not javax.servlet
// The servlet configuration is in the web.xml file
// The alternative approach would be like below
// @WebServlet(urlPatterns = { "/book/addbook", "/book/books" })
public class BookServlet extends HttpServlet
{
	private static final long serialVersionUID = -4755981094229859819L;

	@Inject
	public BookRepo bookRepo;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// here i have trimmed the .jsp from the browser urls
		// and redirected the request to jps files with the relevant content
		if (request.getRequestURI().endsWith("/addbook"))
		{
			// this is http://localhost:8080/JakartaEJB/book/addbook
			request.setAttribute("book", new Book());
			// i can directly redirect to "addbook.jsp" even though it is in the "book" folder (see webapp folder)
			// because i am already in the /book mappings (see web.xml)
			request.getRequestDispatcher("addbook.jsp").forward(request, response);
		}
		else if (request.getRequestURI().endsWith("/books"))
		{
			// this is http://localhost:8080/JakartaEJB/book/books
			request.setAttribute("books", bookRepo.getBooks());
			// i can directly redirect to "list.jsp" even though it is in the "book" folder (see webapp folder)
			// because i am already in the /book mappings (see web.xml)
			request.getRequestDispatcher("list.jsp").forward(request, response);
		}
		// here "request.setAttribute" is actually "modelandview.addobject" in spring framework
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// book post mappings, same logic as above
		if (request.getRequestURI().endsWith("/addbook"))
		{
			// request.getParameter("name") fetches the value from <input type="text" name="name"/>
			// maybe there is a way to bind objects in JSP files to mapped classes or DTOs
			bookRepo.save(new Book(request.getParameter("name"), Integer.parseInt(request.getParameter("year")), request.getParameter("author")));
			response.sendRedirect("addbook.jsp");
		}
	}

}
