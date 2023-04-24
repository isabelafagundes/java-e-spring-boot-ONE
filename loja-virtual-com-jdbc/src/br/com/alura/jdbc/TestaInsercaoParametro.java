package br.com.alura.jdbc;

import java.sql.*;

public class TestaInsercaoParametro {

    public static void main(String[] args) throws SQLException {

        ConnectionFactory factory = new ConnectionFactory();
        try ( Connection connection = factory.recuperarConexao()) {
            connection.setAutoCommit(false);
            //Desabilita o commit automático para o banco.

            try ( PreparedStatement stn = connection.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
                    //Para evitar a influência do erro do usuário na compilação, usamos o PreparedStatement
                    //o qual trata o que é inserido pelo usuário, evitando o SQL Injection (Usuário inserir comandos
                    // SQL como parâmetro).
            ) {
                adicionarVariavel("SmartTv", "SmartTv 35 polegadas", stn);
                adicionarVariavel("Rádio", "Rádio Bluetooth", stn);
                //Quando o método adicionarVariavel ocorrer sem problemas,
                connection.commit();
                //então o commit será executado.
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ROLLBACK EXECUTADO");
                connection.rollback();
                //Caso ocorra alguma falha na transação, todas as alterações devem ser revertidas,
                //assim executamos o rollback.
            }
        }

    }

    private static void adicionarVariavel(String nome, String descricao, PreparedStatement stn) throws SQLException {
            stn.setString(1, nome);
            stn.setString(2, descricao);
            //O PreparedStatement insere parâmetros não compiláveis, ou seja torna a aplicação mais segura
            //pois o usuário não poderá inserir comandos que podem danificar a aplicação, como um comando DELETE.

            if (nome.equals("Rádio")) {
                throw new RuntimeException("Não foi possível adicionar o produto");
            }
            stn.execute();

            try (ResultSet rst = stn.getGeneratedKeys()) {
                while (rst.next()) {
                    Integer id = rst.getInt(1);
                    System.out.println("O id criado foi: " + id);
                }
            }
    }

}
