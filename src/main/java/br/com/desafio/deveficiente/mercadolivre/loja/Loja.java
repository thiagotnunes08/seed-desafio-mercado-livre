package br.com.desafio.deveficiente.mercadolivre.loja;

import br.com.desafio.deveficiente.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Loja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToMany
    private Set<Usuario> responsaveis;

    private Boolean aberto = Boolean.FALSE;

    @Deprecated
    public Loja() {
    }


    public void abrirLoja() {
        setAberto(true);
    }

    public void fecharLoja() {
        setAberto(false);

    }
    public void setAberto(Boolean aberto) {
        this.aberto = aberto;
    }
}
