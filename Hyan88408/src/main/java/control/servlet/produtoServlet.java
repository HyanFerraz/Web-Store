package control.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.dao.ProdutoDAO;
import modelo.entidades.Categoria;
import modelo.entidades.Produto;

/**
 * Servlet implementation class produtoServlet
 */
@WebServlet(name = "produto", urlPatterns = { "/produto" })
public class produtoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public produtoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Produto produto = new Produto();
		Categoria categoria = new Categoria();
		
		produto.setNome(request.getParameter("nomeProduto"));
		produto.setDescricao(request.getParameter("descricaoProduto"));
		produto.setPreco(Double.parseDouble(request.getParameter("precoProduto")));
		categoria.setId(Integer.parseInt(request.getParameter("categoria")));
		produto.setCategoria(categoria);
		
		new ProdutoDAO().inserir(produto);
		
	}

}
