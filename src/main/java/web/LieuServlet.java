package web;

import java.io.IOException;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.Response;
import dao.LieuDaoImpl;
import dao.ILieuDao;
import metier.entities.Lieu;

@WebServlet (name="lieuServ",urlPatterns= {"/lieus","/saisieLieu",
"/saveLieu","/supprimerLieu","/editerLieu","/updateLieu"})

public class LieuServlet extends HttpServlet{

	ILieuDao metier;
	@Override
	public void init() throws ServletException {
	metier = new LieuDaoImpl();
	}
	@Override
	protected void doGet(HttpServletRequest request,
	HttpServletResponse response)
	throws ServletException, IOException {
	String path=request.getServletPath();
	System.out.println("PATH "+path);
	if (path.equals("/lieus"))
	{
	LieuModel model= new LieuModel();
	List<Lieu> ls = metier.getAllLieus();
	model.setLieus(ls);
	request.setAttribute("model", model);
	request.getRequestDispatcher("lieus.jsp").forward(request,response);
	}
	else if (path.equals("/saisieLieu") )
	{
	request.getRequestDispatcher("saisieLieu.jsp").forward(request,response
	);
	}
	else if (path.equals("/saveLieu") && request.getMethod().equals("POST"))

			{
			String nomPays=request.getParameter("pays");


			Lieu lieu = metier.save(new Lieu(nomPays));
			request.setAttribute("lieu", lieu);
			response.sendRedirect("lieus");
			}
			else if (path.equals("/supprimerlieu"))
			{
			Long id= Long.parseLong(request.getParameter("id"));
			metier.deleteLieu(id);
			response.sendRedirect("lieus");
			}
			else if (path.equals("/editerLieu") )
			{
			Long id= Long.parseLong(request.getParameter("id"));
			Lieu l = metier.getLieu(id);
			request.setAttribute("lieu", l);
			request.getRequestDispatcher("editerLieu.jsp").forward(request,response
			);
			}
			else if (path.equals("/updateLieu") )
			{
			Long id = Long.parseLong(request.getParameter("id"));
			String nomPays=request.getParameter("pays");
			Lieu lieu = new Lieu();
			lieu.setidLieu(id);
			lieu.setpays(nomPays);

			metier.updateLieu(lieu);
			response.sendRedirect("lieus");
			}
			else
			{
			response.sendError(Response.SC_NOT_FOUND);
			}
			}
			@Override
			protected void doPost(HttpServletRequest request,

			HttpServletResponse response) throws

			ServletException, IOException {
			doGet(request,response);
			}
}
