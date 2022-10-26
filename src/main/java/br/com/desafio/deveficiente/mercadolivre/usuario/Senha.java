package br.com.desafio.deveficiente.mercadolivre.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

public class Senha {

    private String senha;

    public Senha(String senha) {
        Assert.hasLength(senha, "senha nao pode estar em branco");

        Assert.isTrue(senha.length() >= 6, "senha deve ter no minimo 6 caracteres");
        this.senha = senha;
    }

    public String hash() {
        return new BCryptPasswordEncoder().encode(senha);
    }
    public String getSenha() {
        return senha;
    }


}
