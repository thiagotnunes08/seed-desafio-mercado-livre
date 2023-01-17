package br.com.desafio.deveficiente.mercadolivre.nf;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "nfClient", url = "http://localhost:8080/nf")
public interface NotaFiscalClient {

    @PostMapping
    String processa(DadosDaCompraRequest dadosDaCompraRequest);
}
