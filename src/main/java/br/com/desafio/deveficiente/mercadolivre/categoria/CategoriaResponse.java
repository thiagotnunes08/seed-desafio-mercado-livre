package br.com.desafio.deveficiente.mercadolivre.categoria;

public class CategoriaResponse {

    private String nome;

    public CategoriaResponse(Categoria categoria) {
        this.nome = categoria.getNome();
    }

    public String getNome() {
        return nome;
    }
}
