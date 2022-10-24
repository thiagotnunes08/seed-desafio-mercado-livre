package br.com.desafio.deveficiente.mercadolivre.usuario;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private final LocalDateTime cadastradoEm = LocalDateTime.now();

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    /**
     * uso hibernate
     */
    @Deprecated
    public Usuario() {
    }
}
