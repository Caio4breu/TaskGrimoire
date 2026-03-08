package caio4breu.taskgrimoire.infra;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Caio 4breu
 */
public class ConexaoDB {
    public static Connection conectar() throws SQLException {
        try {
            Properties props = new Properties();
            InputStream input = ConexaoDB.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties");
            props.load(input);
            return DriverManager.getConnection(
                props.getProperty("db.url"),
                props.getProperty("db.usuario"),
                props.getProperty("db.senha")
            );
        } catch (IOException e) {
            throw new SQLException("Erro ao carregar configuracoes do banco.", e);
        }
    }
}