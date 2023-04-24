package br.com.alura.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestaRemocao {

    public static void main(String[] args) throws SQLException {

        ConnectionFactory conFactory = new ConnectionFactory();
        Connection con = conFactory.recuperarConexao();
        //Recupera conexão

        PreparedStatement stn = con.prepareStatement("DELETE FROM PRODUTO WHERE ID > ?");
        stn.setInt(1, 2);
        //Insire no paramêtro de índice 1 o número inteiro 2
        stn.execute();

        Integer linhasModificadas = stn.getUpdateCount();
        //Retorna o número de linhas modificadas
        System.out.println("Número de linhas modificadas: " + linhasModificadas);

        stn.close();
        con.close();
    }

}
