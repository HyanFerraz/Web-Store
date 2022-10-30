package modelo.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import modelo.entidades.Categoria;

public class CategoriaDAO extends DAO{

	public void inserir(Categoria categoria) {
		connection = conexao.conectar();
		sql = "INSERT INTO JAVA_CATEGORIA (CATEGORIA_ID, CATEGORIA) VALUES (CATEGORIA_SEQUENCE.NEXTVAL, ?)";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, categoria.getCategoria());
			ps.execute();
			
			ps.close();
			
		} catch (SQLException e) {
			System.out.println("Erro ao inserir categoria " + e) ;
		}
		
		conexao.desconectar();
		
	}
	
	public Map<Integer, Categoria> listar() {
		
		Map<Integer, Categoria> lista = new HashMap<Integer, Categoria>();
		Categoria categoria;
		
		connection = conexao.conectar();
		sql = "SELECT * FROM JAVA_CATEGORIA";

		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				categoria = new Categoria();
				categoria.setId(rs.getInt("CATEGORIA_ID"));
				categoria.setCategoria(rs.getString("CATEGORIA"));
				
				lista.put(categoria.getId(), categoria);
				
			}
			
			ps.close();
		} catch (SQLException e) {
			System.out.println("Erro ao buscar categorias " + e);
		}
		
		conexao.desconectar();
		return lista;
	}
	
}
