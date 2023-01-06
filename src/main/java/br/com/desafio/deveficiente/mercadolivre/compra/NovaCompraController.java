package br.com.desafio.deveficiente.mercadolivre.compra;

import br.com.desafio.deveficiente.mercadolivre.produto.ProdutoRepository;
import br.com.desafio.deveficiente.mercadolivre.util.EnviaEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RequestMapping("/api/v1/compras")
@RestController
public class NovaCompraController {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EnviaEmail enviaEmail;

    @PostMapping
    @Transactional
    public ResponseEntity<?> executa(@RequestBody @Valid NovaCompraRequest request, UriComponentsBuilder builder) throws BindException {

        var novaCompra = request.toModel(produtoRepository);

        compraRepository.save(novaCompra);

        enviaEmail.envia(novaCompra.login(), "OBA!,nova compra.", "voce tem uma nova compra de:" + request.getComprador());

        if (request.getGatewayPagamento().equals(GatewayPagamento.pagseguro)) {

            var pagseguro = builder
                    .path("pagseguro.com?returnId={idGeradoDaCompra}&redirectUrl={urlRetornoAppPosPagamento}")
                    .buildAndExpand(novaCompra.getId(), "pagseguro/pagamentos")
                    .toUri();

            return ResponseEntity.created(pagseguro).body(new CompraResponse(novaCompra));
        }
        var paypal = builder
                .path("paypal.com?returnId={idGeradoDaCompra}&redirectUrl={urlRetornoAppPosPagamento}")
                .buildAndExpand(novaCompra.getId(), "paypal/pagamentos")
                .toUri();

        return ResponseEntity.created(paypal).body(new CompraResponse(novaCompra));
    }
}
