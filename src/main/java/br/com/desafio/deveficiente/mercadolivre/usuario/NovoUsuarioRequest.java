package br.com.desafio.deveficiente.mercadolivre.usuario;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NovoUsuarioRequest {

    @NotBlank
    private String nome;
    @NotBlank
    @Email
    private String userName;
    @NotBlank
    @Length(min = 6)
    private String senha;


    public NovoUsuarioRequest(String nome, String userName, String senha) {
        this.userName = userName;
        this.senha = senha;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public String getUserName() {
        return userName;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario toModel() {
        return new Usuario(nome, userName,new Senha(senha));
    }



}
