package br.com.desafio.deveficiente.mercadolivre.produto;

import javax.validation.constraints.NotBlank;

public class CaracteristicasDeProdutoRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    public CaracteristicasDeProdutoRequest(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public CaracteristicasDeProduto toCaractreisticas(){
        return new CaracteristicasDeProduto(nome,descricao);
    }

}
