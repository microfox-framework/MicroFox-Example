package ir.moke.example.persistence;

import ir.moke.example.MainClass;
import ir.moke.microfox.persistence.BatisExecutor;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class DB {

    public static void initializeMyBatis() {
        Configuration configuration = new Configuration();
        Environment environment = new Environment("h2", new JdbcTransactionFactory(), getDataSource());
        configuration.setEnvironment(environment);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.addMappers("ir.moke.example.persistence.mapper");
        BatisExecutor.configure(configuration);
    }

    private static DataSource getDataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:testdb;MODE=Oracle;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false");
        dataSource.setUser("sa");
        dataSource.setPassword("sa");
        return dataSource;
    }

    public static void initializeDatabase() {
        try {
            DataSource dataSource = getDataSource();
            Connection connection = dataSource.getConnection();
            List<String> lines = loadSqlScript();
            if (lines != null) {
                for (String line : lines) {
                    Statement statement = connection.createStatement();
                    statement.execute(line);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> loadSqlScript() {
        try (InputStream inputStream = MainClass.class.getClassLoader().getResourceAsStream("initialize_db.sql")) {
            if (inputStream != null) {
                String content = new String(inputStream.readAllBytes());
                return Arrays.stream(content.replaceAll("\\n", "").replaceAll("\\s+", " ").split(";")).toList();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
