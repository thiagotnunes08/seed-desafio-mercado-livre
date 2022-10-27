package br.com.desafio.deveficiente.mercadolivre.categoria;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class NovaCategoriaRequest {

    @NotBlank
    private String nome;
    @Positive
    private Long idCategoriaMae;

    public String getNome() {
        return nome;
    }


    public Long getIdCategoriaMae() {
        return idCategoriaMae;
    }

    public Categoria toModel(EntityManager manager) {

        Categoria categoria = new Categoria(nome);
        if (idCategoriaMae != null) {
            Categoria categoriaMae = manager.find(Categoria.class, idCategoriaMae);
            Assert.notNull(categoriaMae, "o id da categoria mae deve ser válido!");
            categoria.setMae(categoriaMae);
        }
        return categoria;
    }
}
