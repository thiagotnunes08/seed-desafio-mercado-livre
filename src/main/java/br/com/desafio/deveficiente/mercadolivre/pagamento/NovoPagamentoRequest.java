package br.com.desafio.deveficiente.mercadolivre.pagamento;
import br.com.desafio.deveficiente.mercadolivre.compra.CompraRepository;
import br.com.desafio.deveficiente.mercadolivre.compra.GatewayPagamento;
import javax.validation.constraints.NotNull;
public class NovoPagamentoRequest {

    @NotNull
    private Long compraId;
    private Status status;


    public Long getCompraId() {
        return compraId;
    }

    public Pagamento toModel(CompraRepository compraRepository) {

        var compra = compraRepository.findById(compraId).get();

        var gatewayPagamento = compra.getGatewayPagamento();

        if (gatewayPagamento.equals(GatewayPagamento.paypal)) {
            this.status = Status.UM;

        } else if (gatewayPagamento.equals(GatewayPagamento.pagseguro)) {
            this.status = Status.SUCESSO;
        }

        return new Pagamento(compra, status);

    }
}





