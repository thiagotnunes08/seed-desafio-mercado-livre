package br.com.desafio.deveficiente.mercadolivre.categoria;

import javax.persistence.*;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @ManyToOne
    private Categoria categoriaMae;
    public Categoria(String nome ) {
        this.nome = nome;
    }

    /**
     * @Deprecated, uso hibernate
     */
    public Categoria() {
    }

    public void setMae(Categoria categoriaMae) {
        this.categoriaMae = categoriaMae;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", categoriaMae=" + categoriaMae +
                '}';
    }

    public String getNome() {
        return nome;
    }
}
