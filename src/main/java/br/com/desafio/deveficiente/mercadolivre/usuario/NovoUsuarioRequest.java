package br.com.desafio.deveficiente.mercadolivre.usuario;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NovoUsuarioRequest {

    @NotBlank
    @Email
    private String login;
    @NotBlank
    @Length(min = 6)
    private String senha;


    public NovoUsuarioRequest(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public String toString() {
        return "NovoUsuarioRequest{" +
                "login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }

    public Usuario toModel() {
        return new Usuario(login,new Senha(senha));
    }
}
