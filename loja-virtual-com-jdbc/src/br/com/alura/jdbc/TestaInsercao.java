package br.com.alura.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {

    public static void main(String[] args) throws SQLException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.recuperarConexao();
        //Recupera a conexão

        Statement stn = connection.createStatement();
        stn.execute("INSERT INTO PRODUTO (nome, descricao) VALUES ('MOUSE', 'MOUSE GAMER')",
                Statement.RETURN_GENERATED_KEYS);
        //Executa comandos sql e retorna as chaves criadas

        ResultSet rst = stn.getGeneratedKeys();
        //A list de chaves geradas

        while (rst.next()){
            Integer id = rst.getInt(1); //acessando a primeira coluna da table
            System.out.println("O id criado foi: " + id);
        }

    }

}
