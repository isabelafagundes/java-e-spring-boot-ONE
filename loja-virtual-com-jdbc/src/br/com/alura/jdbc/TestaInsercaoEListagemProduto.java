package br.com.alura.jdbc;

import br.com.alura.jdbc.dao.ProdutoDAO;
import br.com.alura.jdbc.modelo.Produto;

import java.sql.*;
import java.util.List;

public class TestaInsercaoEListagemProduto {
    public static void main(String[] args) throws SQLException {

        Produto computador = new Produto("Computador", "Computador com LED");
        //Inserindo um objeto Produto no BD

        try (Connection con = new ConnectionFactory().recuperarConexao()) {
            ProdutoDAO produtoDAO =  new ProdutoDAO(con);
            produtoDAO.salvar(computador);
            List<Produto> listaProdutos = produtoDAO.listar();
            listaProdutos.stream().forEach(lp -> System.out.println(lp));
            //Percorre a lista de produtos.
        }
    }
}
