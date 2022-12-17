package br.com.desafio.deveficiente.mercadolivre.produto;

import br.com.desafio.deveficiente.mercadolivre.categoria.CaracteristicasDeProdutoResponse;
import br.com.desafio.deveficiente.mercadolivre.categoria.CategoriaResponse;
import br.com.desafio.deveficiente.mercadolivre.produto.opniao.OpiniaoResponse;
import br.com.desafio.deveficiente.mercadolivre.produto.pergunta.Pergunta;
import br.com.desafio.deveficiente.mercadolivre.produto.pergunta.PerguntaResponse;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class DetalhaProdutoResponse {

    private final long qtdNota;
    private String nome;

    private BigDecimal valor;

    private Integer quantidadeDisponivel;

    private List<CaracteristicasDeProdutoResponse> caracteristicas = new ArrayList<>();

    private String descricao;

    private CategoriaResponse categoria;

    private LocalDateTime criadoEm;

    private List<String> imagens = new ArrayList<>();

    private List<OpiniaoResponse> opnioes = new ArrayList<>();

    private BigDecimal mediaNotas;


    private List<PerguntaResponse> perguntas;

    //TODO deixar a relação de Pergunta e Produto bidirecional.
    public DetalhaProdutoResponse(Produto produto, List<Pergunta> perguntas) {
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.quantidadeDisponivel = produto.getQuantidadeDisponivel();


        /**
         * TODO:LER
         * Implementação fina do Alberto. Mais enxuta e orientada a objetos.
         * Além de nao expor nossa coleção de caracteristicas, para q nao possa ser utilizada de maneira indevida.
         * como é no caso de "Opnioes e Perguntas nos casos adiante;
         */
        this.caracteristicas = produto.mapeiaCaracteristicas(CaracteristicasDeProdutoResponse::new);


        this.descricao = produto.getDescricao();
        this.categoria = new CategoriaResponse(produto.getCategoria());
        this.criadoEm = produto.getCriadoEm();
        this.imagens = produto.getImagems();

        this.opnioes = produto.getOpnioes()
                .stream()
                .map(OpiniaoResponse::new)
                .collect(Collectors.toList());
        //TODO nao há necessidade de passar o objeto de reponse, sendo que precisamos so do titulo, Uma string
        this.perguntas = perguntas.stream()
                .map(PerguntaResponse::new)
                .collect(Collectors.toList());
        //TODO, criar uma collection de opnioes e isolar logicas la
        double mediaNota = opnioes.stream().mapToInt(OpiniaoResponse::getNota).average().orElse(0.0);
        this.mediaNotas = new BigDecimal(mediaNota).setScale(1, RoundingMode.HALF_UP);

        this.qtdNota = opnioes.stream().map(OpiniaoResponse::getNota).count();


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

    public BigDecimal getMediaNotas() {
        return mediaNotas;
    }

    public long getQtdNota() {
        return qtdNota;
    }
}
