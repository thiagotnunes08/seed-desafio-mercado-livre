package br.com.desafio.deveficiente.mercadolivre.compra;

import br.com.desafio.deveficiente.mercadolivre.produto.Produto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

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


    public String login(){
        return produto.getDono().getLogin();
    }

}
