package br.com.desafio.deveficiente.mercadolivre.pagamento;

import br.com.desafio.deveficiente.mercadolivre.compra.Compra;

public interface RetornoGatewayPagamento {

    /**
     *
     * @param compra
     * @return um novo pagamento em função do gateway especifico
     */
    Pagamento toPagamentos(Compra compra);
}
