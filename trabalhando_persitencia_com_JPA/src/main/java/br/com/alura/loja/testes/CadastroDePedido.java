package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.*;
import br.com.alura.loja.util.JPAUtil;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDePedido {
    public static void main(String[] args) {
        popularBancoDeDados();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        ClienteDao clienteDao =  new ClienteDao(em);

        Produto produto1 = produtoDao.buscarPorId(1l);
        Produto produto2 = produtoDao.buscarPorId(2l);
        Cliente cliente = clienteDao.buscarPorId(1l);

        em.getTransaction().begin();

        Pedido pedido1= new Pedido(cliente);
        pedido1.adicionar(new ItemPedido(10, pedido1, produto1));

        Pedido pedido2 = new Pedido(cliente);
        pedido2.adicionar(new ItemPedido(2, pedido2, produto1));
        pedido2.adicionar(new ItemPedido(1, pedido2, produto2));

        PedidoDao pedidoDao = new PedidoDao(em);
        pedidoDao.cadastrar(pedido1);
        pedidoDao.cadastrar(pedido2);

        em.getTransaction().commit();

        BigDecimal totalVendido = pedidoDao.valorTotalVendido();
        System.out.println("VALOR TOTAL: " + totalVendido);

        List<RelatorioDeVendasVo> relatorio = pedidoDao.relatorioDeVendas();
        relatorio.forEach(System.out::println);

    }

    private static void popularBancoDeDados() {
        Categoria categoriaCelulares = new Categoria("Celulares");
        Categoria categoriaTelevisoes = new Categoria("Televisoes");
        Categoria categoriaRadios= new Categoria("Radios");

        Produto celular = new Produto("Xiaomi Redmi", "128GB",new BigDecimal("800"), categoriaCelulares);
        Produto televisao = new Produto("Televisão Philco", "SmartTv 32 polegadas", new BigDecimal("2000"), categoriaTelevisoes);
        Produto radio = new Produto("JBL Bluetooth", "Rádio Bluetooth JBL", new BigDecimal("200"), categoriaRadios);

        Cliente cliente =  new Cliente("Rodrigo", "00000000000");

        EntityManager em = JPAUtil.getEntityManager();

        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ClienteDao clienteDao =  new ClienteDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(categoriaCelulares);
        categoriaDao.cadastrar(categoriaTelevisoes);
        categoriaDao.cadastrar(categoriaRadios);

        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(televisao);
        produtoDao.cadastrar(radio);

        clienteDao.cadastrar(cliente);

        em.getTransaction().commit();
        em.close();
    }
}
