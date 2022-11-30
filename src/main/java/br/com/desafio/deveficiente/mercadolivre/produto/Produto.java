package br.com.desafio.deveficiente.mercadolivre.produto;

import br.com.desafio.deveficiente.mercadolivre.categoria.Categoria;
import br.com.desafio.deveficiente.mercadolivre.usuario.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @ElementCollection
    private List<CaracteristicasDeProduto> caracteristicas = new ArrayList<>();

    @Column(nullable = false)
    private String descricao;

    @ManyToOne(optional = false)
    private Categoria categoria;

    @Column(nullable = false)
    private LocalDateTime criadoEm;

    @ManyToOne
    private Usuario dono;

    @ElementCollection
    private List<String> imagems = new ArrayList<>();

    public Produto(String nome, BigDecimal valor, Integer quantidadeDisponivel, List<CaracteristicasDeProduto> caracteristicas, String descricao, Categoria categoria, Usuario dono) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.caracteristicas.addAll(caracteristicas);
        this.descricao = descricao;
        this.categoria = categoria;
        criadoEm = LocalDateTime.now();
        this.dono = dono;
        Assert.isTrue(caracteristicas.size() >= 3,
                "deveria haver no minimo, tres caracteristicas aki");

    }

    @Deprecated
    public Produto() {
    }


    public List<CaracteristicasDeProduto> getCaracteristicas() {
        return caracteristicas;
    }

    public void adicionaEVerificaUser(List<String> imagem, String usuarioLogado){
        if (!dono.getLogin().equals(usuarioLogado)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"usuário não é dono do produto!");
        }
        imagems.addAll(imagem);
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
                ", criadoEm=" + criadoEm +
                ", dono=" + dono +
                ", imagems=" + imagems +
                '}';
    }
}
