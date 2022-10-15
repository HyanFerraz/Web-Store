package modelo.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import modelo.dao.DAO;

public class Conexao extends DAO {
	private final String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
	private final String driver = "oracle.jdbc.driver.OracleDriver";
	private final String user = "rm88408";
	private final String password = "301001";
	
	private Connection conexao;
	
	public Connection conectar() {
		try {
			Class.forName(driver);
			conexao = DriverManager.getConnection(url, user, password);			
			if (!conexao.isClosed()) 
				System.out.println("Conectado");
		}
		catch(ClassNotFoundException e) {
			System.out.println("Erro ao carregar o driver");
		}
		catch(SQLException e) {
			System.out.println("Erro ao estabelecer conex�o com o banco de dados");
		}
		
		return conexao;
	}
	
	public void desconectar() {
		try {
			conexao.close();
		}
		catch(SQLException e) {
			System.out.println("Erro ao desconectar do banco de dados\n" + e);
		}
	}
	
}