package modelo.dao;

import java.sql.SQLException;

import modelo.entidades.Pedido;

public class PedidoDAO extends DAO {
	
	public void inserir (Pedido pedido) {
		
		connection = conexao.conectar();
		sql = "INSERT INTO JAVA_PEDIDO (PEDIDO_ID, NOME_CONTATO, ENDERECO_CONTATO, DATA) VALUES (PEDIDO_SEQUENCE.NEXTVAL, ?, ?, TO_DATE( ? , 'YYYY/MM/DD')) ";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, pedido.getNome());
			ps.setString(2, pedido.getEndereco());
			ps.setString(3, pedido.getData());
			ps.execute();
			
			ps.close();
		} catch (SQLException e) {
			System.out.println("Erro ao inserir pedido " + e);
		}
		
		conexao.desconectar();
	}
	
	public void remover(Pedido pedido) {
		
		connection = conexao.conectar();
		sql = "DELETE FROM JAVA_PEDIDO WHERE PEDIDO_ID = ?";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, pedido.getId());
			ps.execute();
			
			ps.close();
		} catch (SQLException e) {
			System.out.println("Erro ao excluir pedido " + e);
		}
		
		conexao.desconectar();
	}

	public Pedido buscar() {
		
		Pedido pedido = new Pedido();
		connection = conexao.conectar();
		sql = "SELECT * FROM JAVA_PEDIDO WHERE PEDIDO_ID = (SELECT MAX(PEDIDO_ID) FROM JAVA_PEDIDO)";
		
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				pedido.setId(rs.getInt("PEDIDO_ID"));
				pedido.setNome(rs.getString("NOME_CONTATO"));
				pedido.setEndereco(rs.getString("ENDERECO_CONTATO"));
				pedido.setData(rs.getString("DATA"));
			}
			
			ps.close();
			
		} catch (SQLException e) {
			System.out.println("Erro ao buscar produto por ID " + e);
		}
		
		conexao.desconectar();
		return pedido;
	}
}
