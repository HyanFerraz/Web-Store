package modelo.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.conexao.Conexao;
import modelo.entidades.Categoria;
import modelo.entidades.Produto;

public class ProdutoDAO extends DAO {

	public void inserir (Produto produto) {
		
		Conexao conexao = new Conexao();
		connection = conexao.conectar();
		sql = "INSERT INTO JAVA_PRODUTO (PRODUTO_ID, CATEGORIA_ID, NOME, DESCRICAO, PRECO) VALUES (PRODUTO_SEQUENCE.NEXTVAL, ?, ?, ?, ?)";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, produto.getCategoria().getId());
			ps.setString(2, produto.getNome());
			ps.setString(3, produto.getDescricao());
			ps.setDouble(4, produto.getPreco());
			ps.execute();
			
			ps.close();
		} catch (SQLException e) {
			System.out.println("Erro ao inserir produto " + e);
		}
		
		conexao.desconectar();
		
	}

	public List<Produto> listar () {
		
		List<Produto> lista = new ArrayList<Produto>();
		Produto produto;
		Categoria categoria;
		Conexao conexao = new Conexao();
		
		connection = conexao.conectar();
		sql = "SELECT P.PRODUTO_ID, P.NOME, P.DESCRICAO, P.PRECO, C.CATEGORIA FROM JAVA_PRODUTO P, JAVA_CATEGORIA C WHERE P.CATEGORIA_ID = C.CATEGORIA_ID";
		
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				categoria = new Categoria();
				produto = new Produto();
				
				produto.setId(rs.getInt("PRODUTO_ID"));
				produto.setNome(rs.getString("NOME"));
				produto.setDescricao(rs.getString("DESCRICAO"));
				produto.setPreco(rs.getDouble("PRECO"));
				categoria.setCategoria(rs.getString("CATEGORIA"));
				produto.setCategoria(categoria);
				lista.add(produto);
			}
			
			ps.close();
			
		} catch (SQLException e) {
			System.out.println("Erro ao listar Produtos " + e);
			
		}
		
		conexao.desconectar();
		return lista;
			
	}

	public Produto buscar (int id) {
		
		Produto produto = new Produto();
		Categoria categoria = new Categoria();
		Conexao conexao = new Conexao();
		connection = conexao.conectar();
		sql = "SELECT P.NOME, P.DESCRICAO, P.PRECO, C.CATEGORIA FROM JAVA_PRODUTO P, JAVA_CATEGORIA C WHERE P.CATEGORIA_ID = C.CATEGORIA_ID AND P.PRODUTO_ID = ?";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				produto.setId(id);
				produto.setNome(rs.getString("NOME"));
				produto.setDescricao(rs.getString("DESCRICAO"));
				produto.setPreco(rs.getDouble("PRECO"));
				categoria.setCategoria(rs.getString("CATEGORIA"));
				produto.setCategoria(categoria);
			}
			
			ps.close();			
			
		} catch (SQLException e) {
			System.out.println("Erro ao buscar produto por ID " + e);
		}
		
		conexao.desconectar();
		return produto;
	}
}
