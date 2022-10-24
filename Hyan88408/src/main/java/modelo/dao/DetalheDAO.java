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
					
		} catch (SQLException e) {
			System.out.println("Erro ao inserir detalhe " + e);
		}
		
	}

	public List<Detalhe> listar () {
		
		List<Detalhe> lista = new ArrayList<Detalhe>();
		Conexao conexao = new Conexao();
		Detalhe detalhe;
		
		connection = conexao.conectar();
		sql = "SELECT PE.NOME_CONTATO, PR.NOME AS PRODUTO, PE.DATA, PD.QUANTIDADE, PD.TOTAL FROM JAVA_PEDIDO PE, JAVA_PEDIDODETALHE PD, JAVA_PRODUTO PR WHERE";
		
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				detalhe = new Detalhe();
				produto = new Produto();
				pedido = new Pedido();
				
				pedido.setNome(rs.getString("NOME_CONTATO"));
				produto.setNome(rs.getString("PRODUTO"));
				pedido.setData(rs.getString("DATA"));
				detalhe.setQuantidade(rs.getInt("QUANTIDADE"));
				detalhe.setTotal(rs.getDouble("TOTAL"));
				
				detalhe.setProduto(produto);
				detalhe.setPedido(pedido);
				
				lista.add(detalhe);
			}
			
			ps.close();
			conexao.desconectar();
			return lista;
		} catch (SQLException e) {
			System.out.println("Erro ao listar Produtos " + e);
			return null;
		}
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
			conexao.desconectar();
		} catch (SQLException e) {
			System.out.println("Erro ao remover detalhe de pedido " + e);
			}
		
		
	}
}
