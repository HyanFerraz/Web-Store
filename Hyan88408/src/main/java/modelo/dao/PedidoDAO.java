package modelo.dao;

import java.sql.SQLException;

import modelo.conexao.Conexao;
import modelo.entidades.Pedido;

public class PedidoDAO extends DAO {

	
	public void adicionarPedido (Pedido pedido) {
		
		Conexao conexao = new Conexao();
		connection = conexao.conectar();
		sql = "INSERT INTO JAVA_PEDIDO P AND JAVA_PEDIDODETALHE PD,"
			+ "(P.PEDIDO_ID, P.NOME_CONTATO, P.ENDERECO_CONTATO, P.DATA) "
			+ "VALUES (?, ?, ?, ?)";
		
	}
	
	public void removerPedido(Pedido pedido) {
			
		Conexao conexao = new Conexao();
		connection = conexao.conectar();
		sql = "DELETE FROM JAVA_PEDIDO P AND JAVA_PEDIDODETALHE PD WHERE P.PEDIDO_ID = PD.PEDIDO_ID = ?";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, pedido.getId());
			ps.execute();
			
			ps.close();
			conexao.desconectar();
		} catch (SQLException e) {
			System.out.println("Erro ao excluir pedido " + e);
		}
	}
	
}
