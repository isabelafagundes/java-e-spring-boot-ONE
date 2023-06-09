package br.com.alura.loja.testes;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.CategoriaId;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {
	
	public static void main(String[] args) {
		cadastrarProduto();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);

		Produto p = produtoDao.buscarPorId(1l);
		System.out.println(p.getPreco());

		produtoDao.buscarTodos();
		List<Produto> todos = produtoDao.buscarPorNomeDaCategoria("Celulares");
		todos.forEach(produto -> System.out.println(produto.getNome()));

		BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoPeloNome("Xiaomi Redmi");
		System.out.println("Preço: " + precoDoProduto);
	}

	private static void cadastrarProduto() {
		Categoria categoriaCelulares = new Categoria("Celulares");
		Produto celular = new Produto("Xiaomi Redmi", "Muito legal",new BigDecimal("800"), categoriaCelulares);

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);

		em.getTransaction().begin();
		categoriaDao.cadastrar(categoriaCelulares);
		produtoDao.cadastrar(celular);
		em.getTransaction().commit();
		em.find(Categoria.class, new CategoriaId("CELULARES", "TIPO"));
		em.close();
	}
}
