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

@WebServlet(name = "pedido", urlPatterns = { "/pedido" })
public class PedidoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PedidoServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Pedido pedido = new Pedido();
		Detalhe detalhe = new Detalhe();
		Produto produto = new Produto();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		PedidoDAO pedidoDAO = new PedidoDAO();
		DetalheDAO detalheDAO = new DetalheDAO();
		
		System.out.println(request.getParameter("id"));
		/*
		pedido.setNome(request.getParameter("nomeContato"));
		pedido.setEndereco(request.getParameter("endereco"));
		pedido.setData(request.getParameter("data"));
		
		pedidoDAO.inserir(pedido);
		
		detalhe.setPedido(pedido);
		detalhe.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
		detalhe.setTotal(produto.getPreco() * detalhe.getQuantidade());
		detalhe.setProduto(produtoDAO.buscar(Integer.parseInt(request.getParameter(""))));
		
		detalheDAO.inserir(detalhe);
		*/
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		
		dispatcher.forward(request, response);
		
	}

}
