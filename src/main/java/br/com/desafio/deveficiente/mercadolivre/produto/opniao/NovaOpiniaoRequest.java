package br.com.desafio.deveficiente.mercadolivre.produto.opniao;

import br.com.desafio.deveficiente.mercadolivre.produto.Produto;
import br.com.desafio.deveficiente.mercadolivre.usuario.Usuario;
import br.com.desafio.deveficiente.mercadolivre.usuario.UsuarioRepository;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.util.Optional;

public class NovaOpiniaoRequest {

    @Min(1)
    @Max(5)
    private Integer nota;
    @NotBlank
    private String titulo;
    @NotBlank
    @Length(max = 500)
    private String descricao;
    @NotNull
    private Long produtoId;



    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    @Override
    public String toString() {
        return "NovaOpiniaoRequest{" +
                "nota=" + nota +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", produtoId=" + produtoId +
                '}';
    }

    public Opiniao toModel(String usuarioLogado, Produto produto, UsuarioRepository repository) {

        Usuario usuario = repository
                .findByLogin(usuarioLogado)
                .orElseThrow(()
                        -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario nao encontrado no sistema!"));

        return new Opiniao(nota,titulo,descricao,produto,usuario);
    }
}
