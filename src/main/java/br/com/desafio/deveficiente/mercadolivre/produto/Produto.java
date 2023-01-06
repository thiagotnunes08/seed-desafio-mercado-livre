package br.com.desafio.deveficiente.mercadolivre.produto;

import br.com.desafio.deveficiente.mercadolivre.categoria.Categoria;
import br.com.desafio.deveficiente.mercadolivre.produto.caracteristicas.CaracteristicasDeProduto;
import br.com.desafio.deveficiente.mercadolivre.produto.opniao.Opiniao;
import br.com.desafio.deveficiente.mercadolivre.usuario.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "produto", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Opiniao> opnioes = new ArrayList<>();

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

    public void adicionaEVerificaUser(List<String> imagem, String usuarioLogado) {
        if (!dono.getLogin().equals(usuarioLogado)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "usuário não é dono do produto!");
        }
        imagems.addAll(imagem);
    }


    public void adicionaOpiao(Opiniao opiniao) {
        opnioes.add(opiniao);
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setQuantidadeDisponivel(Integer quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public Usuario getDono() {
        return dono;
    }

    public List<String> getImagems() {
        return imagems;
    }

    public List<Opiniao> getOpnioes() {
        return opnioes;
    }

    public <T> List<T> mapeiaCaracteristicas(Function<CaracteristicasDeProduto, T> mapper) {
        return this.caracteristicas.stream().map(mapper).collect(Collectors.toList());
    }

    public boolean abateValorNoEstoque(@Positive @NotNull Integer quantidadeASerAbatida) {

        Assert.isTrue(this.quantidadeDisponivel > 0, "não é possivel abater estoque negativo");
        if (quantidadeASerAbatida <= this.quantidadeDisponivel) {
            this.quantidadeDisponivel -= quantidadeASerAbatida;
            return true;
        }
        return false;
    }

}
