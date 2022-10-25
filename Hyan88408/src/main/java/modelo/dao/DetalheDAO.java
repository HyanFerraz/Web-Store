package modelo.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.conexao.Conexao;
import modelo.entidades.Detalhe;
import modelo.entidades.Pedido;
import modelo.entidades.Produto;

public class DetalheDAO extends DAO{
	
	Produto produto;
	Pedido pedido;
	
	public void inserir(Detalhe detalhe) {
		Conexao conexao = new Conexao();
		connection = conexao.conectar();
		sql = "INSERT INTO JAVA_PEDIDODETALHE (ID, PEDIDO_ID, PRODUTO_ID, QUANTIDADE, TOTAL) VALUES (DETALHE_SEQUENCE.NEXTVAL, ?, ?, ?, ?)";
	
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, detalhe.getPedido().getId());
			ps.setInt(2, detalhe.getProduto().getId());
			ps.setInt(3, detalhe.getQuantidade());
			ps.setDouble(4, detalhe.getTotal());
			ps.execute();
			
			ps.close();
			
		} catch (SQLException e) {
			System.out.println("Erro ao inserir detalhe " + e);
		}
		
		conexao.desconectar();
	}

	public List<Detalhe> listar () {
		
		List<Detalhe> lista = new ArrayList<Detalhe>();
		Conexao conexao = new Conexao();
		Detalhe detalhe;
		
		connection = conexao.conectar();
		sql = "SELECT PE.NOME_CONTATO, PR.NOME AS PRODUTO,  TO_DATE(PE.DATA, 'YYYY/MM/DD') AS DATA, PD.QUANTIDADE, PD.TOTAL, PD.PEDIDO_ID  "
				+ "FROM JAVA_PEDIDODETALHE PD, JAVA_PEDIDO PE, JAVA_PRODUTO PR "
				+ "WHERE PD.PEDIDO_ID = PE.PEDIDO_ID AND PD.PRODUTO_ID = PR.PRODUTO_ID";
		
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				detalhe = new Detalhe();
				produto = new Produto();
				pedido = new Pedido();
				
				pedido.setNome(rs.getString("NOME_CONTATO"));
				pedido.setId(rs.getInt("PEDIDO_ID"));
				pedido.setData(rs.getString("DATA"));
				produto.setNome(rs.getString("PRODUTO"));
				detalhe.setQuantidade(rs.getInt("QUANTIDADE"));
				detalhe.setTotal(rs.getDouble("TOTAL"));
				
				detalhe.setProduto(produto);
				detalhe.setPedido(pedido);
				
				lista.add(detalhe);
			}
			
			ps.close();
			
		} catch (SQLException e) {
			System.out.println("Erro ao listar Detalhes " + e);
		}
		
		conexao.desconectar();
		return lista;
	}

	public void remover(Detalhe detalhe) {
		
		Conexao conexao = new Conexao();
		connection = conexao.conectar();
		sql = "DELETE FROM JAVA_PEDIDODETALHE WHERE PEDIDO_ID = ? ";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, detalhe.getPedido().getId());
			ps.execute();
			
			ps.close();
		} catch (SQLException e) {
			System.out.println("Erro ao remover detalhe de pedido " + e);
			}
		
		conexao.desconectar();
		
	}
}
