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
    private String login;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private LocalDateTime cadastradoEm;

    public Usuario(String login, Senha senha) {
        this.login = login;
        Assert.isTrue(StringUtils.hasLength(login),"email nao poderia estar em branco!");
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

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", cadastradoEm=" + cadastradoEm +
                '}';
    }

    public Long getId() {
        return id;
    }
}
