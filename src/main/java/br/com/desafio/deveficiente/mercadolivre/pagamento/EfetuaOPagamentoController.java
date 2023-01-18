package br.com.desafio.deveficiente.mercadolivre.pagamento;
import br.com.desafio.deveficiente.mercadolivre.compra.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;

@RestController
public class EfetuaOPagamentoController {


    @Autowired
    private final CompraRepository compraRepository;
    @Autowired
    private final EventosNovaCompra eventosNovaCompra;

    public EfetuaOPagamentoController(CompraRepository compraRepository, EventosNovaCompra eventosNovaCompra) {
        this.compraRepository = compraRepository;
        this.eventosNovaCompra = eventosNovaCompra;
    }

    @PostMapping("/paypal/pagamentos")
    @Transactional
    public String executa(@Valid @RequestBody ConfirmaPagamentoPaypalRequest request) {

        return processa(request.getCompraId(), request);

    }
    //TODO colocar validacao de unicidade no idTransacao
    @PostMapping("/pagseguro/pagamentos")
    @Transactional
    public String executa(@Valid @RequestBody ConfirmaPagamentoPagSeguroRequest request) {

        return processa(request.getCompraId(), request);
    }

    private String processa(Long id, RetornoGatewayPagamento retornoGatewayPagamento) {

        var compra = compraRepository
                .findById(id)
                .orElseThrow(() ->
                new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                        "Compra inválida, ou não efetuada no sistema!"));

        compra.adicionaPagamento(retornoGatewayPagamento);

        compraRepository.save(compra);

        eventosNovaCompra.processa(compra);

        return compra.toString();
    }
}

