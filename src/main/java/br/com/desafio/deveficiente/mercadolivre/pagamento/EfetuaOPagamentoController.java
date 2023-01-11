package br.com.desafio.deveficiente.mercadolivre.pagamento;

import br.com.desafio.deveficiente.mercadolivre.compra.CompraRepository;
import br.com.desafio.deveficiente.mercadolivre.util.EnviaEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class EfetuaOPagamentoController {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    PagamentoRepository pagamentoRepository;

    @Autowired
    EnviaEmail enviaEmail;


    @PostMapping("/pagamentos")
    @Transactional
    public String executa(@Valid @RequestBody NovoPagamentoRequest request) {


        var pagamento = request.toModel(compraRepository);

        pagamentoRepository.save(pagamento);

        //TODO: trocar de string simples para usuario entidade
        System.out.println(enviaEmail.envia("thiagotomasnunes08@gmail.com",
                "Pagamento aprovado",
                "Olá," + pagamento.getComprador() + " está tudo certo com sua compra!"));

        pagamento.finalizaCompra();

        return pagamento.toString();


    }
}
