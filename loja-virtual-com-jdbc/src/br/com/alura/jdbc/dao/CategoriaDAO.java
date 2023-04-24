package br.com.alura.jdbc.dao;

import br.com.alura.jdbc.modelo.Categoria;
import br.com.alura.jdbc.modelo.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    //Data Acess Object - DAO

    public Connection connection;
    //Recebe uma conexão em seu construtor.

    public CategoriaDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();

        System.out.println("Executando a Query de listar categorias...");

        String sql = "SELECT ID, NOME FROM CATEGORIA";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.execute();

            try (ResultSet rst = pstm.getResultSet()) {
                while (rst.next()) {
                    Categoria categoria = new Categoria(rst.getInt(1), rst.getString(2));
                    categorias.add(categoria);
                }
            }
        }
        return categorias;
    }

    public List<Categoria> listarComProdutos() throws SQLException {
        Categoria ultima = null;
        List<Categoria> categorias = new ArrayList<>();

        System.out.println("Executando a Querry de listar categorias...");

        String sql = "SELECT C.ID, C.NOME, P.ID, P.NOME, P.DESCRICAO FROM CATEGORIA C INNER JOIN PRODUTO P ON C.ID = P.CATEGORIA_ID";
        //INNER JOIN é a cláusula que permite a junção entre tabelas, desde que haja um entrelaçamento entre elas.

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.execute();

            try (ResultSet rst = pstm.getResultSet()) {
                while (rst.next()) {
                    if (ultima == null || !ultima.getNome().equals(rst.getString(2))) {
                        Categoria categoria = new Categoria(rst.getInt(1), rst.getString(2));
                        ultima = categoria;
                        categorias.add(categoria);
                    }
                    Produto produto = new Produto(rst.getInt(3), rst.getString(4), rst.getString(5));
                    ultima.adicionar(produto);
                }
            }
        }
        return categorias;
    }
}
