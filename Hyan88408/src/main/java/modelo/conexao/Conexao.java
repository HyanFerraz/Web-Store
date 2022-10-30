package modelo.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao{
	private final String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
	private final String driver = "oracle.jdbc.driver.OracleDriver";
	private final String user = "";
	private final String password = "";
	
	private Connection conexao;
	

	public Connection conectar() {
		try {
			Class.forName(driver);
			conexao = DriverManager.getConnection(url, user, password);			
			if (!conexao.isClosed()) 
				System.out.println("Conectado");
			
		}
		catch(ClassNotFoundException e) {
			System.out.println("Erro ao carregar o driver " + e);
		}
		catch(SQLException e) {
			System.out.println("Erro ao estabelecer conexão com o banco de dados " + e);
		}
		return conexao;
		
	}
	

		public void desconectar() {
		try {
			conexao.close();
			System.out.println("Desconectado");
		}
		catch(SQLException e) {
			System.out.println("Erro ao desconectar do banco de dados\n" + e);
		}
	}
	
}