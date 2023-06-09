package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.*;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class PerformanceConsultas {

    public static void main(String[] args) {
        popularBancoDeDados();
        EntityManager em = JPAUtil.getEntityManager();
        PedidoDao pedidoDao = new PedidoDao(em);
        Pedido pedido1= pedidoDao.buscarPedidoComliente(1l);
        em.close();
        System.out.println("Cliente: " + pedido1.getCliente().getNome());
        /*
        Exibe o cliente mesmo com o EntityManager fechado, pois em
        buscarPedidoComCliente carregamos este relacionamento em uma query planejada:
        */
    }

    private static void popularBancoDeDados(){

        Categoria celulares = new Categoria("CELULAR");
        Categoria videoGames = new Categoria("VIDEO GAMES");
        Categoria informatica = new Categoria("INFORMATICA");

        Produto celular = new Produto("Samsung A32", "Samsung A32 AZUL", new BigDecimal("1000"), celulares);
        Produto videoGame = new Produto("Nintendo Switch", "Nintendo Switch Lite", new BigDecimal("1700"), videoGames);
        Produto notebook = new Produto("Lenovo ideapad330", "Notebook Lenovo ideapad330", new BigDecimal("2000"), informatica);

        Cliente cliente = new Cliente("Isabela", "00000000000");

        Pedido pedido = new Pedido(cliente);
        pedido.adicionar(new ItemPedido(1, pedido, celular));
        pedido.adicionar(new ItemPedido(1, pedido, videoGame));

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ClienteDao clienteDao = new ClienteDao(em);
        PedidoDao pedidoDao = new PedidoDao(em);

        em.getTransaction().begin(); //iniciar a transação
        //Cadastrar categorias no banco de dados:
        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(videoGames);
        categoriaDao.cadastrar(informatica);
        //Cadastrar produtos no banco de dados:
        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(videoGame);
        produtoDao.cadastrar(notebook);
        //Cadastrar clientes no banco de dados:
        clienteDao.cadastrar(cliente);
        //Cadastrar pedido no banco de dados:
        pedidoDao.cadastrar(pedido);
        //Commitar alterações:
        em.getTransaction().commit();
        //Fechar transação:
        em.close();


    }

}
