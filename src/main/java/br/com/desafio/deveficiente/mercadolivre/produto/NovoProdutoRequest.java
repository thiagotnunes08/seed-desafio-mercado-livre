package br.com.desafio.deveficiente.mercadolivre.produto;

import br.com.desafio.deveficiente.mercadolivre.categoria.Categoria;
import br.com.desafio.deveficiente.mercadolivre.categoria.CategoriaRepository;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;

public class NovoProdutoRequest {

    @NotBlank
    private String nome;
    @DecimalMin(value = "0")
    @NotNull
    private BigDecimal valor;
    /**
     * tanto em bigDecimal quanto em Integer. Poderia ter utilizado @Positive para nao deixar entrar numeros negativos.
     */
    @NotNull
    @Min(value = 0)
    private Integer quantidadeDisponivel;
    @Valid
    @NotNull
    //TODO testar @Size. pois é necessário ter pelo menos 3 caracteristicas.
    private CaracteristicasDeProdutoRequest caracteristicas;
    @NotBlank
    @Length(max = 1000)
    private String descricao;
    @NotNull
    //TODO Colocar uma annotacion "ExistsId" por exemplo
    private Long categoriaId;


    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public CaracteristicasDeProdutoRequest getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }


    public Produto toModel(CategoriaRepository categoriaRepository) {

        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Categoria não encontrada no sistema!"));

        return new Produto(nome,valor,quantidadeDisponivel,caracteristicas.toModel(),descricao,categoria);
    }
}
