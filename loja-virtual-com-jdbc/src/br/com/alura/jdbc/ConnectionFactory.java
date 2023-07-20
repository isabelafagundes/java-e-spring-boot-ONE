package br.com.alura.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    //Facilita a construção de objetos, as classes apenas precisarão instanciar esta classe para recuperar a conexão!

    public DataSource dataSource;
    //Expõe as informções do pool de conexões.

    public ConnectionFactory() {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        //Cria o pool de conexões
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/lojavirtual?useTimezone=true&serverTimezone=UTC");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("");

        comboPooledDataSource.setMaxPoolSize(15);
        //15 conexões máximas

        this.dataSource = comboPooledDataSource;
    }

    public Connection recuperarConexao() throws SQLException {
        return this.dataSource.getConnection();
        //Resgata uma conexão, das que estão disponiveis no pool de conexões.
    }
}
