package br.com.desafio.deveficiente.mercadolivre.produto;

import br.com.desafio.deveficiente.mercadolivre.categoria.CaracteristicasDeProdutoResponse;
import br.com.desafio.deveficiente.mercadolivre.categoria.Categoria;
import br.com.desafio.deveficiente.mercadolivre.categoria.CategoriaResponse;
import br.com.desafio.deveficiente.mercadolivre.produto.opniao.OpiniaoResponse;
import br.com.desafio.deveficiente.mercadolivre.produto.pergunta.Pergunta;
import br.com.desafio.deveficiente.mercadolivre.produto.pergunta.PerguntaResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Média de notas do produto TODO
 */

public class ProdutoResponse {

    private String nome;

    private BigDecimal valor;

    private Integer quantidadeDisponivel;

    private List<CaracteristicasDeProdutoResponse> caracteristicas = new ArrayList<>();

    private String descricao;

    private CategoriaResponse categoria;

    private LocalDateTime criadoEm;

    private List<String> imagens = new ArrayList<>();

    private List<OpiniaoResponse> opnioes = new ArrayList<>();

    private List<PerguntaResponse> perguntas;





    public ProdutoResponse(Produto produto, List<Pergunta> perguntas) {
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.quantidadeDisponivel = produto.getQuantidadeDisponivel();
        this.caracteristicas = produto
                .getCaracteristicas()
                .stream()
                .map(CaracteristicasDeProdutoResponse::new)
                .collect(Collectors.toList());
        this.descricao = produto.getDescricao();
        this.categoria = new CategoriaResponse(produto.getCategoria());
        this.criadoEm = produto.getCriadoEm();
        this.imagens = produto.getImagems();
        this.opnioes = produto.getOpnioes()
                .stream()
                .map(OpiniaoResponse::new)
                .collect(Collectors.toList());

        this.perguntas = perguntas.stream()
                .map(PerguntaResponse::new)
                .collect(Collectors.toList());




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

    public List<CaracteristicasDeProdutoResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public CategoriaResponse getCategoria() {
        return categoria;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public List<String> getImagens() {
        return imagens;
    }

    public List<OpiniaoResponse> getOpnioes() {
        return opnioes;
    }

    public List<PerguntaResponse> getPerguntas() {
        return perguntas;
    }
}
