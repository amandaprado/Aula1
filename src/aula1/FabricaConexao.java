
package aula1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {
    // definição de qual banco será utilizado
    private static final String STR_DRIVER = "org.gjt.mm.mysql.Driver";
    private static final String DATABASE = "bancoo"; // Nome do banco de dados
    private static final String IP = "127.0.0.1";  // ip de conexao
    // string de conexao com o banco de dados
    private static final String STR_CON = "jdbc:mysql://" + IP + ":3306/" + DATABASE;
    private static final String USER = "root"; // Nome do usuário
    private static final String PASSWORD = ""; // senha
    private static Connection objConexao = null;

    public FabricaConexao() throws SQLException {
        try {
            Class.forName(STR_DRIVER);
            objConexao = DriverManager.getConnection(STR_CON, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            String errorMsg = "Driver nao encontrado: " + e.getMessage();
            System.out.println(errorMsg);
        } catch (SQLException e) {
            String errorMsg = "Erro ao obter a conexao: " + e.getMessage();
            System.out.println(errorMsg);
        }
    }

    public static Connection GeraConexao() throws SQLException {
        if (objConexao == null) {
            FabricaConexao MANTERCONEXAO = new FabricaConexao();
        }
        return objConexao;
    }
}
