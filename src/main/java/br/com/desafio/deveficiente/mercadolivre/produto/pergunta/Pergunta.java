package br.com.desafio.deveficiente.mercadolivre.produto.pergunta;
import br.com.desafio.deveficiente.mercadolivre.produto.Produto;
import br.com.desafio.deveficiente.mercadolivre.usuario.Usuario;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @ManyToOne(optional = false)
    private Produto produto;

    @ManyToOne(optional = false)
    private Usuario autor;

    @Column(nullable = false)
    private LocalDateTime criadaEm;

    public Pergunta(String titulo, Produto produto, Usuario autor) {
        this.titulo = titulo;
        this.produto = produto;
        this.autor = autor;
        this.criadaEm = LocalDateTime.now();
    }
    @Deprecated
    public Pergunta() {
    }

    @Override
    public String toString() {
        return "Pergunta{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", produto=" + produto +
                ", autor=" + autor +
                ", criadaEm=" + criadaEm +
                '}';
    }

    public String enviaEmail() {
        return "Nova pergunta do usuário:" + autor.getLogin()
                + ", sobre o produto " + produto.getNome() + " ''" + titulo + "''";
    }

    public String getTitulo() {
        return titulo;
    }
}
