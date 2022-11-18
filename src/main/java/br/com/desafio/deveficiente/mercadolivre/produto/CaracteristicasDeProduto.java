package br.com.desafio.deveficiente.mercadolivre.produto;

import javax.persistence.Embeddable;

@Embeddable
public class CaracteristicasDeProduto {

    private String marca;
    private String modelo;
    private String material;
    private String estilo;

    public CaracteristicasDeProduto(String marca, String modelo, String material, String estilo) {
        this.marca = marca;
        this.modelo = modelo;
        this.material = material;
        this.estilo = estilo;
    }

    @Deprecated
    public CaracteristicasDeProduto() {
    }

}
