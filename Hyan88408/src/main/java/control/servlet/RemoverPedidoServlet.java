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
import modelo.entidades.Detalhe;
import modelo.entidades.Pedido;


@WebServlet(name = "removerPedido", urlPatterns = { "/removerPedido" })
public class RemoverPedidoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemoverPedidoServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Pedido pedido = new Pedido();
		PedidoDAO pedidoDAO = new PedidoDAO();
		Detalhe detalhe = new Detalhe();
		DetalheDAO detalheDAO = new DetalheDAO();
		
		pedido.setId(Integer.parseInt(request.getParameter("id")));
		detalhe.setPedido(pedido);
		
		detalheDAO.remover(detalhe);
		pedidoDAO.remover(pedido);

		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		
		dispatcher.forward(request, response);
		
	}

}
