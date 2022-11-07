package br.com.desafio.deveficiente.mercadolivre.security;

import br.com.desafio.deveficiente.mercadolivre.usuario.Usuario;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

public class UserAuth extends User {

    private String usuario;
    private String senha;

    public UserAuth(Usuario usuario) {
        super(usuario.getLogin(),usuario.getSenha(), Collections.emptyList());

        this.usuario = usuario.getLogin();
        this.senha = usuario.getSenha();
    }
}
