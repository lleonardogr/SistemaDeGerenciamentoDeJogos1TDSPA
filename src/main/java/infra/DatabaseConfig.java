package infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String URL =
            "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private static final String USER = "pf1779";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void Initialize(){
        var sql = """
                CREATE TABLE jogo (
                 id NUMBER PRIMARY KEY,
                 nome VARCHAR2(255) NOT NULL,
                 data_lancamento DATE,
                 plataforma VARCHAR2(50) NOT NULL,
                 media_avaliacoes NUMBER NOT NULL)
                """;
        try(var conn = getConnection()){
            var stmt = conn.prepareStatement(sql);
            stmt.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}