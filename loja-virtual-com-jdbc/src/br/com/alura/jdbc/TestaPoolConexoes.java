package br.com.alura.jdbc;

import java.sql.SQLException;

public class TestaPoolConexoes {

    public static void main(String[] args) throws SQLException {

        ConnectionFactory connectionFactory = new ConnectionFactory();

        for (int i = 0; i < 20; i++) {
            connectionFactory.recuperarConexao();
            System.out.println("Conexão de número: " + i);
            //Requisita 20 conexões porém o tamanho máximo do pool de conexão é 15
            //Para um cliente rquisitar uma conexão ao ultrapassar o limite de tamanho
            //ele deverá aguardar até uma conexão estar disponível.
        }

    }

}
