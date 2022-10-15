package modelo.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidades.Detalhe;
import modelo.entidades.Pedido;
import modelo.entidades.Produto;

public class DetalheDAO extends DAO{
	
	Produto produto;
	Pedido pedido;
	
	public void inserir(Detalhe detalhe) {
		
		connection = conexao.conectar();
		sql = "INSERT INTO JAVA_PEDIDODETALHE (ID, PEDIDO_ID, PRODUTO_ID, QUANTIDADE, TOTAL) VALUES (DETALHE_SEQUENCE.NEXTVAL, ?, ?, ?, ?)";
	
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, detalhe.getPedido().getId());
			ps.setInt(2, detalhe.getProduto().getId());
			ps.setInt(3, detalhe.getQuantidade());
			ps.setDouble(4, detalhe.getTotal());
			ps.execute();
					
		} catch (SQLException e) {
			System.out.println("Erro ao inserir detalhe " + e);
		}
		
	}

	public List<Detalhe> listar() {
		
		List<Detalhe> lista = new ArrayList<>();

		
		connection = conexao.conectar();
		sql = "SELECT P.NOME_CONTATO AS CLIENTE, PR.NOME AS PRODUTO, P.DATA, D.QUANTIDADE, D.TOTAL "
			+ "FROM JAVA_PEDIDO P,JAVA_PEDIDODETALHE D, JAVA_PRODUTO PR "
			+ "WHERE D.PEDIDO_ID = P.PEDIDO_ID AND D.PRODUTO_ID = PR.PRODUTO_ID";
		
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			Detalhe detalhe;
			
			while (rs.next()) {
				produto = new Produto();
				pedido = new Pedido();
				detalhe = new Detalhe();
				
				pedido.setNome(rs.getString("CLIENTE"));
				produto.setNome(rs.getString("PRODUTO"));
				pedido.setData(rs.getDate("DATA"));
				detalhe.setQuantidade(rs.getInt("QUANTIDADE"));
				detalhe.setTotal(rs.getDouble("TOTAL"));
				detalhe.setPedido(pedido);
				detalhe.setProduto(produto);
				lista.add(detalhe);
				
				ps.close();
				conexao.desconectar();
				return lista;
			}
			
			ps.close();
			conexao.desconectar();
		} catch (SQLException e) {
			System.out.println("Erro ao listar Pedidos " + e);
		}
		
		return lista;
	}
}
