package br.com.desafio.deveficiente.mercadolivre.produto;

import br.com.desafio.deveficiente.mercadolivre.categoria.Categoria;
import br.com.desafio.deveficiente.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private Integer quantidadeDisponivel;

    @Embedded
    private CaracteristicasDeProduto caracteristicas;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne(optional = false)
    private Categoria categoria;

    @Column(nullable = false)
    private LocalDateTime criadoEm;

    @ManyToOne
    private Usuario dono;

    public Produto(String nome, BigDecimal valor, Integer quantidadeDisponivel, CaracteristicasDeProduto caracteristicas, String descricao, Categoria categoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoria = categoria;
        criadoEm = LocalDateTime.now();
    }

    @Deprecated
    public Produto() {
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidadeDisponivel=" + quantidadeDisponivel +
                ", caracteristicas=" + caracteristicas +
                ", descricao='" + descricao + '\'' +
                ", categoria=" + categoria +
                '}';
    }
}
