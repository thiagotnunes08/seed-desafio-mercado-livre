package br.com.desafio.deveficiente.mercadolivre.produto;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CaracteristicasDeProduto {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Deprecated
    public CaracteristicasDeProduto() {
    }

    public CaracteristicasDeProduto(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "CaracteristicasDeProduto{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
