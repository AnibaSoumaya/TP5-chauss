package web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.Response;

import dao.ChaussureDaoImpl;
import dao.ILieuDao;
import dao.LieuDaoImpl;
import dao.iChaussDao;
import metier.entities.Chaussure;
import metier.entities.Lieu;

@WebServlet(name = "cs", urlPatterns = { "/controleur", "*.do" })
public class ControleurServlet extends HttpServlet {
	iChaussDao metier;
	ILieuDao metierLieu;

	@Override
	public void init() throws ServletException {
		metier = new ChaussureDaoImpl();
		metierLieu = new LieuDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.equals("/index.do")) {
			request.getRequestDispatcher("chaussures.jsp").forward(request, response);
		} else if (path.equals("/chercher.do")) {
			String motCle = request.getParameter("motCle");
			ChaussureModele model = new ChaussureModele();
			model.setMotCle(motCle);
			List<Chaussure> prods = metier.chaussuresParMC(motCle);
			model.setChaussures(prods);
			request.setAttribute("model", model);
			request.getRequestDispatcher("chaussures.jsp").forward(request, response);
		} else if (path.equals("/saisie.do")) {
			List<Lieu> lieu = metierLieu.getAllLieus();
			LieuModel model= new LieuModel();
			model.setLieus(lieu);
			request.setAttribute("lieuModel", model);
			request.getRequestDispatcher("saisieChaussure.jsp").forward(request, response);
			
		} else if (path.equals("/save.do") && request.getMethod().equals("POST")) {
			
			String nom = request.getParameter("nom");
			Long lieuId=Long.parseLong(request.getParameter("lieu"));
			double prix = Double.parseDouble(request.getParameter("prix"));
			Lieu l = metierLieu.getLieu(lieuId);
			Chaussure c = metier.save(new Chaussure(nom, prix,l));
			request.setAttribute("chaussure", c);
			response.sendRedirect("chercher.do?motCle=");
			
		} else if (path.equals("/supprimer.do")) {
			
			Long id = Long.parseLong(request.getParameter("id"));
			metier.deleteChaussure(id);
			response.sendRedirect("chercher.do?motCle=");
			
		} else if (path.equals("/editer.do")) {
			
			Long id = Long.parseLong(request.getParameter("id"));
			Chaussure p = metier.getChaussure(id);
			request.setAttribute("chaussure", p);
			
			List<Lieu> ls = metierLieu.getAllLieus();
			LieuModel model= new LieuModel();
			model.setLieus(ls);
			request.setAttribute("lieuModel", model);
			
			request.getRequestDispatcher("editerChaussure.jsp").forward(request, response);
			
			
		} else if (path.equals("/update.do")) {
			
			Long id = Long.parseLong(request.getParameter("id"));
			String nom = request.getParameter("nom");
			double prix = Double.parseDouble(request.getParameter("prix"));
			Long lieuId=Long.parseLong(request.getParameter("lieu"));
			Chaussure p = new Chaussure();
			p.setIdChaussure(id);
			p.setNomChaussure(nom);
			p.setPrix(prix);
			Lieu l = metierLieu.getLieu(lieuId);
			p.setLieu(l);
			metier.updateChaussure(p);
			request.setAttribute("Chaussure", p);
		} else {
			response.sendError(Response.SC_NOT_FOUND);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}