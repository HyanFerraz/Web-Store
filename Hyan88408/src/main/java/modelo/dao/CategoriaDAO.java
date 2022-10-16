package modelo.dao;

import java.sql.SQLException;

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
			conexao.desconectar();
		} catch (SQLException e) {
			System.out.println("Erro ao inserir categoria " + e) ;
		}
		
		
	}
	
}
