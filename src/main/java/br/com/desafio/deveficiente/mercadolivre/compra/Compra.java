package br.com.desafio.deveficiente.mercadolivre.compra;

import br.com.desafio.deveficiente.mercadolivre.pagamento.Pagamento;
import br.com.desafio.deveficiente.mercadolivre.pagamento.RetornoGatewayPagamento;
import br.com.desafio.deveficiente.mercadolivre.produto.Produto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.util.Assert.isTrue;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Produto produto;
    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private String comprador;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusCompra statusCompra;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GatewayPagamento gatewayPagamento;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Pagamento> pagamentos = new HashSet<>();


    @Deprecated
    public Compra() {

    }

    public Compra(Produto produto, Integer quantidade, String comprador, GatewayPagamento gatewayPagamento, BigDecimal valorTotal) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.statusCompra = StatusCompra.INICIADA;
        this.gatewayPagamento = gatewayPagamento;
        this.valorTotal = valorTotal;
    }

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getComprador() {
        return comprador;
    }

    public StatusCompra getStatusCompra() {
        return statusCompra;
    }

    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }


    public String login() {
        return produto.getDono().getLogin();
    }


    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                ", comprador='" + comprador + '\'' +
                ", statusCompra=" + statusCompra +
                ", gatewayPagamento=" + gatewayPagamento +
                ", valorTotal=" + valorTotal +
                ", pagamentos=" + pagamentos +
                '}';
    }

    public void finalizaCompra() {
        this.statusCompra = StatusCompra.FINALIZADA;
    }

    //TODO: LER 3.0
    //aki havia um codigo exatamente igual, mudando apenas o argumento "PayPalRequest"
    //temos duas implementacos diferentes, mas o mesmo comportamento, caso classico de POLIMORFISMO
    //não há a necessidade de repetir codigo.
    //pura orientacao objetos
    // 'implements' == ele é
    public void adicionaPagamento(RetornoGatewayPagamento retornoGateway) {

        var novoPagamento = retornoGateway.toPagamentos(this);

        isTrue(!this.pagamentos.contains(novoPagamento), "pagamento já processado no sistema!");

        isTrue(buscaPagamentosConcluidos().isEmpty(), "está compra já foi concluida com sucesso!");

        this.pagamentos.add(novoPagamento);

    }

    private Set<Pagamento> buscaPagamentosConcluidos() {

        var pagamentos = this.pagamentos
                .stream()
                .filter(Pagamento::foiConcluido)
                .collect(Collectors.toSet());

        isTrue(pagamentos.size() <= 1,
                "BUG, nao deveria ter mais de uma pagamento concluido aki!");

        return pagamentos;
    }


    public boolean foiProcessadaComSucesso() {
        return !buscaPagamentosConcluidos().isEmpty();
        //TODO: ler 4.0
        //outra solução sem a necessidade de acrescentar uma negação, logo nao teria a necessidade de testar
        // (test4 da uma olhadinha la).
        //return buscaPagamentosConcluidos().iterator().hasNext();
    }

    public Long getVendedor() {
        return this.produto.getDono().getId();
    }

    public Set<Pagamento> getPagamentos() {
        return pagamentos;
    }
}


