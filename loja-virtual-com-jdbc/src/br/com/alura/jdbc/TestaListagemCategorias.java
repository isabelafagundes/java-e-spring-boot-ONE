package br.com.alura.jdbc;

import br.com.alura.jdbc.modelo.Categoria;
import br.com.alura.jdbc.modelo.Produto;
import br.com.alura.jdbc.dao.CategoriaDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestaListagemCategorias {
    public static void main(String[] args) throws SQLException {
        //Utilizando o try-with-resources não é preciso fechar a conexão,
        //pois ele faz isso por conta própria por estender a interface AutoClosable.
        try (Connection connection = new ConnectionFactory().recuperarConexao()) {
            CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
            List<Categoria> listaCategorias = categoriaDAO.listarComProdutos();
            //Listar categorias com produtos.
            listaCategorias.stream().forEach(ct -> {
                System.out.println(ct.getNome());
                for (Produto produto : ct.getProdutos()) {
                    System.out.println(ct.getNome() + " - " + produto.getNome());
                }
            });
        }
    }
}
