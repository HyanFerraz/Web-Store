package modelo.dao;

import java.sql.SQLException;

import modelo.conexao.Conexao;
import modelo.entidades.Pedido;

public class PedidoDAO extends DAO {
	
	public void inserir (Pedido pedido) {
		
		Conexao conexao = new Conexao();
		connection = conexao.conectar();
		sql = "INSERT INTO JAVA_PEDIDO (PEDIDO_ID, NOME_CONTATO, ENDERECO_CONTATO, DATA) VALUES (PEDIDO_SEQUENCE.NEXTVAL, ?, ?, ?) ";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, pedido.getNome());
			ps.setString(2, pedido.getEndereco());
			ps.setString(3, pedido.getData());
			ps.execute();
			
			ps.close();
			conexao.desconectar();
		} catch (SQLException e) {
			System.out.println("Erro ao inserir pedido " + e);
		}
		
	}
	
	public void remover(Pedido pedido) {
		
		Conexao conexao = new Conexao();
		connection = conexao.conectar();
		sql = "DELETE FROM JAVA_PEDIDO WHERE PEDIDO_ID = ?";
		
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
