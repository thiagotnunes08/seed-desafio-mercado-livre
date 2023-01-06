package br.com.desafio.deveficiente.mercadolivre.compra;

import java.math.BigDecimal;
import java.util.UUID;

public class CompraResponse {

    private Long id;

    private String nomeProduto;

    private BigDecimal valorUnitario;

    private Integer quantidade;

    private String comprador;

    private StatusCompra statusCompra;

    private GatewayPagamento gatewayPagamento;

    private BigDecimal valorTotal;

    public CompraResponse(Compra novaCompra) {
        this.id = novaCompra.getId();
        this.nomeProduto = novaCompra.getProduto().getNome();
        this.valorUnitario = novaCompra.getProduto().getValor();
        this.quantidade = novaCompra.getQuantidade();
        this.comprador = novaCompra.getComprador();
        this.statusCompra = novaCompra.getStatusCompra();
        this.gatewayPagamento = novaCompra.getGatewayPagamento();
        this.valorTotal = novaCompra.getValorTotal();


    }

    public Long getId() {
        return id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
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
}
