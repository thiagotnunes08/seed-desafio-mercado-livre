package br.com.desafio.deveficiente.mercadolivre.categoria;

import br.com.desafio.deveficiente.mercadolivre.produto.caracteristicas.CaracteristicasDeProduto;

public class CaracteristicasDeProdutoResponse {

    private String nome;
    private String descricao;

    public CaracteristicasDeProdutoResponse(CaracteristicasDeProduto caracteristicasDeProduto) {

        this.nome = caracteristicasDeProduto.getNome();
        this.descricao = caracteristicasDeProduto.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
