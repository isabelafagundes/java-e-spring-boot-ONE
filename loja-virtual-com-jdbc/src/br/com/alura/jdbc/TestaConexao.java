package br.com.alura.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class TestaConexao {

    public static void main(String[] args) throws SQLException {
        ConnectionFactory criarCon = new ConnectionFactory();
        Connection connection = criarCon.recuperarConexao();

        System.out.println("Fechando conexão...");
        connection.close();
    }

}
