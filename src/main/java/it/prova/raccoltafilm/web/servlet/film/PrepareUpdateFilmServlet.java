package it.prova.raccoltafilm.web.servlet.film;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.raccoltafilm.service.MyServiceFactory;

/**
 * Servlet implementation class PrepareUpdateFilmServlet
 */
@WebServlet("/PrepareUpdateFilmServlet")
public class PrepareUpdateFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrepareUpdateFilmServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idFilmParam = request.getParameter("idFilm");

		if (!NumberUtils.isCreatable(idFilmParam)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore123.");
			request.getRequestDispatcher("/film/list.jsp").forward(request, response);
			return;
		}

		try {
			request.setAttribute("update_film_attr",
					MyServiceFactory.getFilmServiceInstance().caricaSingoloElementoEager(Long.parseLong(idFilmParam)));
			request.setAttribute("registi_list_attribute",
					MyServiceFactory.getRegistaServiceInstance().listAllElements());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/film/list.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/film/update.jsp").forward(request, response);
	}

}
