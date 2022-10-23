package control.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.dao.CategoriaDAO;
import modelo.entidades.Categoria;

/**
 * Servlet implementation class CategoriaServlet
 */
@WebServlet("/categoria")
public class CategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoriaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Categoria categoria = new Categoria();
		
		
		categoria.setCategoria(request.getParameter("nomeCategoria"));
		
		new CategoriaDAO().inserir(categoria);
		

		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		
		dispatcher.forward(request, response);
		
		
		
		
	}

}
