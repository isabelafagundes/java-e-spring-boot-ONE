package br.com.alura.loja.testes;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TesteComCriteria {

    public static void main(String[] args) {
        cadastrarProduto();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        produtoDao.buscarPorParametrosComCriteria(null, null, LocalDate.now());
    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULAR");
        Categoria videoGames = new Categoria("VIDEO GAMES");
        Categoria informatica = new Categoria("INFORMATICA");

        Produto celular = new Produto("Samsung A32", "Samsung A32 AZUL", new BigDecimal("1000"), celulares);
        Produto videoGame = new Produto("Nintendo Switch", "Nintendo Switch Lite", new BigDecimal("1700"), videoGames);
        Produto notebook = new Produto("Lenovo ideapad330", "Notebook Lenovo ideapad330", new BigDecimal("2000"), informatica);


        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();
        //Cadastrar categorias no banco de dados:
        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(videoGames);
        categoriaDao.cadastrar(informatica);
        //Cadastrar produtos no banco de dados:
        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(videoGame);
        produtoDao.cadastrar(notebook);
        em.getTransaction().commit();
        em.close();
    }
}
