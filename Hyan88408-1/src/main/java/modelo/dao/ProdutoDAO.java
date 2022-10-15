package modelo.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidades.Categoria;
import modelo.entidades.Produto;

public class ProdutoDAO extends DAO {

	public void inserir (Produto produto)
	{
		
		
		connection = conexao.conectar();
		sql = "INSERT INTO JAVA_PRODUTO VALUES (PRODUTO_SEQUENCE.NEXTVAL, ?, ?, ?, ?)";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, produto.getCategoria().getId());
			ps.setString(2, produto.getNome());
			ps.setString(3, produto.getDescricao());
			ps.setDouble(4, produto.getPreco());
			ps.execute();
			
			ps.close();
			conexao.desconectar();
		} catch (SQLException e) {
			System.out.println("Erro ao inserir produto " + e);
		}
		
		
	}

	public List<Produto> listar () {
		
		List<Produto> lista = new ArrayList<Produto>();
		Produto produto;
		Categoria categoria;
		
		connection = conexao.conectar();
		sql = "SELECT P.NOME, P.DESCRICAO, P.PRECO, C.CATEGORIA AS CNOME FROM JAVA_PRODUTO P, JAVA_CATEGORIA C WHERE P.CATEGORIA_ID = C.CATEGORIA_ID";
		
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				categoria = new Categoria();
				produto = new Produto();
				
				produto.setNome(rs.getString("P.NOME"));
				produto.setDescricao(rs.getString("P.DESCRICAO"));
				produto.setPreco(rs.getDouble("P.PRECO"));
				categoria.setCategoria(rs.getString("C.CATEGORIA"));
				produto.setCategoria(categoria);
				lista.add(produto);
			}
			
			ps.close();
			conexao.desconectar();
			return lista;
		} catch (SQLException e) {
			System.out.println("Erro ao listar Produtos " + e);
			return null;
		}
			
	}
}
