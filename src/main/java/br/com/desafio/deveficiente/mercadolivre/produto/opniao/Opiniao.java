package br.com.desafio.deveficiente.mercadolivre.produto.opniao;

import br.com.desafio.deveficiente.mercadolivre.produto.Produto;
import br.com.desafio.deveficiente.mercadolivre.usuario.Usuario;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;

@Entity
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer nota;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne(optional = false)
    private Produto produto;

    @ManyToOne(optional = false)
    private Usuario usuario;

    public Opiniao(Integer nota, String titulo, String descricao, Produto produto, Usuario usuario ) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produto = produto;
        this.usuario = usuario;


    }

    @Deprecated
    public Opiniao() {
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

}
