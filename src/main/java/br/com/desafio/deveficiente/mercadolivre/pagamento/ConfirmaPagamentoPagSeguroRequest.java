package br.com.desafio.deveficiente.mercadolivre.pagamento;
import br.com.desafio.deveficiente.mercadolivre.compra.Compra;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ConfirmaPagamentoPagSeguroRequest implements RetornoGatewayPagamento {

    @NotNull
    private Long compraId;
    @NotNull
    private StatusPagSeguro status;
    @NotBlank
    private  String idTransacao;


    public Long getCompraId() {
        return compraId;
    }

    public StatusPagSeguro getStatus() {
        return status;
    }

    public String getIdTransacao() {
        return idTransacao;
    }

    @Override
    public String toString() {
        return "ConfirmaPagamentoPaypalRequest{" +
                "compraId=" + compraId +
                ", status=" + status +
                ", idTransacao=" + idTransacao +
                '}';
    }

    public Pagamento toPagamentos(Compra compra) {
        return new Pagamento(compra,idTransacao,status.normaliza());
    }
}
