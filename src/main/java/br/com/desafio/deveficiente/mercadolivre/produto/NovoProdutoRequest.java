package br.com.desafio.deveficiente.mercadolivre.produto;
import br.com.desafio.deveficiente.mercadolivre.categoria.Categoria;
import br.com.desafio.deveficiente.mercadolivre.categoria.CategoriaRepository;
import br.com.desafio.deveficiente.mercadolivre.produto.caracteristicas.CaracteristicasDeProduto;
import br.com.desafio.deveficiente.mercadolivre.produto.caracteristicas.CaracteristicasDeProdutoRequest;
import br.com.desafio.deveficiente.mercadolivre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class NovoProdutoRequest {

    @NotBlank
    private String nome;
    @DecimalMin(value = "0")
    @NotNull
    private BigDecimal valor;
    /**
     * TODO:LER
     * tanto em bigDecimal quanto em Integer. Poderia ter utilizado @Positive para nao deixar entrar numeros negativos.
     */
    @NotNull
    @Min(value = 0)
    private Integer quantidadeDisponivel;

    @NotNull
    @Size(min = 3)
    @Valid
    private List<CaracteristicasDeProdutoRequest> caracteristicas = new ArrayList<>();
    @NotBlank
    @Length(max = 1000)
    private String descricao;
    @NotNull
    //TODO Colocar uma annotacion "ExistsId" por exemplo
    private Long categoriaId;

    public NovoProdutoRequest(String nome, BigDecimal valor, Integer quantidadeDisponivel, List<CaracteristicasDeProdutoRequest> caracteristicas, String descricao, Long categoriaId) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.caracteristicas.addAll(caracteristicas);
        this.descricao = descricao;
        this.categoriaId = categoriaId;
    }

    public Produto toModel(CategoriaRepository categoriaRepository, Usuario dono) {
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Categoria não encontrada no sistema!"));

        List<CaracteristicasDeProduto> caracteristicasList = caracteristicas.stream()
                .map(CaracteristicasDeProdutoRequest::toCaractreisticas)
                .collect(Collectors.toList());

        return new Produto(nome,valor,quantidadeDisponivel,caracteristicasList,descricao,categoria,dono);
    }

    public Set<String> buscaCaractreristicas() {

        HashSet<String> nomesIguais = new HashSet<>();

        HashSet<String> resultados = new HashSet<>();

        for (CaracteristicasDeProdutoRequest caracterisca : caracteristicas){
            String nome = caracterisca.getNome();

            if (!nomesIguais.add(nome)){
                resultados.add(nome);

            }
        }
        return resultados;
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

    public List<CaracteristicasDeProdutoRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }
}
