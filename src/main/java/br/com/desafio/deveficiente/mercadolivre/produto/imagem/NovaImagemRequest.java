package br.com.desafio.deveficiente.mercadolivre.produto.imagem;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class NovaImagemRequest {

    @NotEmpty
    private List<String> imagens = new ArrayList<>();

    @NotNull
    private Long produtoId;

    public List<String> getImagens() {
        return imagens;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    @Override
    public String toString() {
        return "NovaImagemRequest{" +
                "imagens=" + imagens +
                ", produtoId=" + produtoId +
                '}';
    }

}
