package br.com.alura.jdbc;

import java.sql.*;

public class TestaListagem {

    public static void main(String[] args) throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.recuperarConexao();

        PreparedStatement stn = connection.prepareStatement("SELECT ID, NOME, DESCRICAO FROM PRODUTO");
        stn.execute();

        ResultSet rst = stn.getResultSet();

        System.out.println(rst);

        while (rst.next()){
            Integer id = rst.getInt("ID");
            System.out.println(id);
            String nome = rst.getString("NOME");
            System.out.println(nome);
            String descricao =  rst.getString("DESCRICAO");
            System.out.println(descricao);
            System.out.println("-------------------------------------");
        }

        System.out.println("Fechando conexão...");
        connection.close();
    }

}
