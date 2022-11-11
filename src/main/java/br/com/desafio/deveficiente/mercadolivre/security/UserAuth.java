package br.com.desafio.deveficiente.mercadolivre.security;

import br.com.desafio.deveficiente.mercadolivre.usuario.Usuario;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

public class UserAuth extends User {

    private String nome;

    public UserAuth(Usuario usuario) {
        super(usuario.getEmail(), usuario.getSenha(), Collections.emptyList());

       this.nome = usuario.getNome();
    }

    public String getNome() {
        return nome;
    }
}
