package infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String URL = loadUrlFromProperties("database.properties", "db.url");
    private static final String USER = loadUrlFromProperties("database.properties", "db.user");
    private static final String PASSWORD = loadUrlFromProperties("database.properties", "db.password");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void Initialize() {
        var sql = """
                CREATE TABLE jogo (
                 id NUMBER GENERATED as IDENTITY PRIMARY KEY ,
                 nome VARCHAR2(255) NOT NULL,
                 data_lancamento DATE,
                 plataforma VARCHAR2(50) NOT NULL,
                 media_avaliacoes NUMBER NOT NULL)
                """;
        try (var conn = getConnection()) {
            var stmt = conn.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException e) {
            if (e.getErrorCode() == 955) { // ORA-00955: name is already used by an existing object
                System.out.println("Tabela 'jogo' já existe, ignorando criação.");
            } else {
                e.printStackTrace();
                throw new RuntimeException("Erro ao inicializar banco de dados", e);
            }
        }
    }

    private static String loadUrlFromProperties(String fileName, String key) {
        try (var input = DatabaseConfig.class.getClassLoader()
                .getResourceAsStream(fileName)) {
            var props = new java.util.Properties();
            props.load(input);
            return props.getProperty(key);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao carregar database.properties", e);
        }
    }
}