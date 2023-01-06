package br.com.desafio.deveficiente.mercadolivre.compra;
import br.com.desafio.deveficiente.mercadolivre.produto.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovaCompraRequest {

    @NotNull
    private Long produtoId;
    @NotBlank
    private String comprador;
    @Positive
    @NotNull
    private Integer quantidade;

    @NotNull
    private GatewayPagamento gatewayPagamento;



    public Compra toModel(ProdutoRepository repository) throws BindException {

        var produto = repository
                .findById(produtoId)
                .orElseThrow(()-> new  ResponseStatusException(HttpStatus.BAD_REQUEST,"Produto não encontrado no sistema!"));

        var valorTotal = produto.getValor().multiply(new BigDecimal(quantidade));

        //TODO LER 2.0
        // sempre que for fazer uma implementação dessa, "olhar" do parametro p dentro. Commo se fosse um mundo. Sem pensar em quem esta chamando
        boolean abateu = produto.abateValorNoEstoque(quantidade);

        if (abateu) {

            return new Compra(produto, quantidade, comprador, gatewayPagamento, valorTotal);
        }

        var problemaComEstoque = new BindException(this, "novaCompraRequest");

        problemaComEstoque.reject(null, "não foi possivel finalizar a compra por conta do estoque, a quantidade dispoivel é "
                + produto.getQuantidadeDisponivel());

        throw problemaComEstoque;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public String getComprador() {
        return comprador;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }

}
