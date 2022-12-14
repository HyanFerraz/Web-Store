package control.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.dao.DetalheDAO;
import modelo.dao.PedidoDAO;
import modelo.dao.ProdutoDAO;
import modelo.entidades.Detalhe;
import modelo.entidades.Pedido;
import modelo.entidades.Produto;

@WebServlet(name = "adicionarPedido", urlPatterns = { "/adicionarPedido" })
public class AdicionarPedidoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdicionarPedidoServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Pedido pedido = new Pedido();
		PedidoDAO pedidoDAO = new PedidoDAO();
		Detalhe detalhe = new Detalhe();		
		DetalheDAO detalheDAO = new DetalheDAO();
		Produto produto = new Produto();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		
		produto = produtoDAO.buscar(Integer.parseInt(request.getParameter("produto")));

		pedido.setNome(request.getParameter("nomeContato"));
		pedido.setEndereco(request.getParameter("endereco"));
		pedido.setData(request.getParameter("data"));
		pedidoDAO.inserir(pedido);
		
		detalhe.setPedido(pedidoDAO.buscar());
		detalhe.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
		detalhe.setProduto(produto);
		detalhe.setTotal(detalhe.getProduto().getPreco() * detalhe.getQuantidade());
		detalheDAO.inserir(detalhe);

		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		
		dispatcher.forward(request, response);
		
	}

}
