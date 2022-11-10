package br.com.desafio.deveficiente.mercadolivre.usuario;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private LocalDateTime cadastradoEm;

    public Usuario(String nome, String userName, Senha senha) {
        this.nome = nome;
        this.userName = userName;
        Assert.isTrue(StringUtils.hasLength(userName),"email nao poderia estar em branco!");
        Assert.notNull(senha, "senha nao deveria ser nula");
        this.senha = senha.hash();
        this.cadastradoEm = now();
    }

    /**
     * uso hibernate
     */
    @Deprecated
    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getSenha() {
        return senha;
    }

    public String getNome() {
        return nome;
    }
}
