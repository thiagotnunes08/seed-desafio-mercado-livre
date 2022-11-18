package br.com.desafio.deveficiente.mercadolivre.produto;

public class CaracteristicasDeProdutoRequest {

    private String marca;
    private String modelo;
    private String material;
    private String estilo;

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMaterial() {
        return material;
    }

    public String getEstilo() {
        return estilo;
    }

    @Override
    public String toString() {
        return "CaracteristicasDeProdutoRequest{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", material='" + material + '\'' +
                ", estilo='" + estilo + '\'' +
                '}';
    }

    public CaracteristicasDeProduto toModel() {
        return new CaracteristicasDeProduto(marca,modelo,material,estilo);
    }
}
