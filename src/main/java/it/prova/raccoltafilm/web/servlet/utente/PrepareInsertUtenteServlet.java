package it.prova.raccoltafilm.web.servlet.utente;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.raccoltafilm.model.Ruolo;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.service.RuoloService;

@WebServlet("/PrepareInsertUtenteServlet")
public class PrepareInsertUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RuoloService ruoloService;

	public PrepareInsertUtenteServlet() {
		this.ruoloService=MyServiceFactory.getRuoloServiceInstance();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Ruolo> listaRuoli=ruoloService.listAll();
			request.setAttribute("ruoli_list_attribute", listaRuoli);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/utente/insert.jsp").forward(request, response);
	}

}
