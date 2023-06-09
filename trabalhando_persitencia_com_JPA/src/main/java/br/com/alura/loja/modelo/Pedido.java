package br.com.alura.loja.modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "valor_total")
    private BigDecimal valorTotal = BigDecimal.ZERO;
    private LocalDate data = LocalDate.now();

    /* Por padrão na JPA -> Estes tipos de relacionamentos automatizam
    a efetuação de um Join com Cliente, sempre que carregar a entidade principal (Pedido)
    ItemPedido possui relacionamentos ManyToOne, ou seja eles serão carregados automaticamente,
    mesmo que você não queira acessá-los diretamente, é um carregamento EAGER por padrão */

    @ManyToOne(fetch = FetchType.LAZY) // Lazy -> Só efetua Join caso seja acessado diretamente.
    private Cliente cliente;

    //Não tem a caracteristica de efetuar um Join automático, só carrega o resultado deste relacionamento
    //se ele for diretamente acessado, carregamento LAZY por padrão.
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL) //Indicar que é um relacionamento bidirecional
    private List<ItemPedido> itens = new ArrayList<>();

    public void adicionar(ItemPedido item){
        //Setar os dois lados do relacionamento bidirecional
        item.setPedido(this);
        this.itens.add(item);
        this.valorTotal = this.valorTotal.add(item.getValor());
    }

    public Pedido() {
    }

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
