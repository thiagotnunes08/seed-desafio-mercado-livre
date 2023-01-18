package br.com.desafio.deveficiente.mercadolivre.pagamento;

import br.com.desafio.deveficiente.mercadolivre.compra.Compra;
import br.com.desafio.deveficiente.mercadolivre.nf.DadosDaCompraRequest;
import br.com.desafio.deveficiente.mercadolivre.nf.NotaFiscalClient;
import br.com.desafio.deveficiente.mercadolivre.util.EnviaEmail;
import br.com.desafio.deveficiente.mercadolivre.vendas.RankingVendasClient;
import br.com.desafio.deveficiente.mercadolivre.vendas.VendaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//TODO: DPS que assistir o novo curso de teste, voltar aki e testar de vdd
@Service
public class EventosNovaCompra {

    @Autowired
    private final NotaFiscalClient nfClient;

    @Autowired
    private final EnviaEmail enviaEmail;

    @Autowired
    private final RankingVendasClient rankingVendasClient;

    public EventosNovaCompra(NotaFiscalClient nfClient, EnviaEmail enviaEmail, RankingVendasClient rankingVendasClient) {
        this.nfClient = nfClient;
        this.enviaEmail = enviaEmail;
        this.rankingVendasClient = rankingVendasClient;
    }

    public void processa(Compra compra) {

        if (compra.foiProcessadaComSucesso()) {

            nfClient.processa(new DadosDaCompraRequest(compra.getComprador(), compra.getId()));

            rankingVendasClient.processa(new VendaRequest(compra.getId(), compra.getVendedor()));

            compra.finalizaCompra();

            //TODO: trocar de string email simples para usuario entidade
            enviaEmail.envia("thiagotomasnunes08@gmail.com",
                    "Pagamento efetuado com sucesso!",
                    "deu bom");

        } else {
            enviaEmail.envia("thiagotomasnunes08@gmail.com",
                    "Pagamento foi recusado!",
                    "deu ruim");

            compra.finalizaCompra();
        }

    }
}
