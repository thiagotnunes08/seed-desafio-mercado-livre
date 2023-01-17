package br.com.desafio.deveficiente.mercadolivre.vendas;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "rakingClient",url = "http://localhost:8080/vendas")
public interface RankingVendasClient {

    @PostMapping
    String processa(VendaRequest request);
}
