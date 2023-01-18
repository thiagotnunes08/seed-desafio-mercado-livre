package br.com.desafio.deveficiente.mercadolivre.pagamento;
import br.com.desafio.deveficiente.mercadolivre.categoria.Categoria;
import br.com.desafio.deveficiente.mercadolivre.compra.Compra;
import br.com.desafio.deveficiente.mercadolivre.compra.GatewayPagamento;
import br.com.desafio.deveficiente.mercadolivre.compra.StatusCompra;
import br.com.desafio.deveficiente.mercadolivre.nf.DadosDaCompraRequest;
import br.com.desafio.deveficiente.mercadolivre.nf.NotaFiscalClient;
import br.com.desafio.deveficiente.mercadolivre.produto.Produto;
import br.com.desafio.deveficiente.mercadolivre.produto.caracteristicas.CaracteristicasDeProduto;
import br.com.desafio.deveficiente.mercadolivre.usuario.Senha;
import br.com.desafio.deveficiente.mercadolivre.usuario.Usuario;
import br.com.desafio.deveficiente.mercadolivre.util.EnviaEmail;
import br.com.desafio.deveficiente.mercadolivre.vendas.RankingVendasClient;
import br.com.desafio.deveficiente.mercadolivre.vendas.VendaRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

class EventosNovaCompraTest {



    @Test
    @DisplayName("compra não foi processada com sucesso")
    void test1() {

        var caracteristicasDeProdutos =
                List.of(new CaracteristicasDeProduto("resistente", "com garantia"),
                        new CaracteristicasDeProduto("key1","val2"),
                        new CaracteristicasDeProduto("key2","val3"));

        var produto = new Produto
                ("TENIS",
                        BigDecimal.TEN,
                        2,caracteristicasDeProdutos,
                        "esportivo",
                        new Categoria("esporte"),
                        new Usuario("thiago",
                                new Senha("123456")));

        var novaCompra = new Compra(produto,
                2,"thiagas",
                GatewayPagamento.pagseguro, BigDecimal.TEN);

        var pagamento = new Pagamento(novaCompra,"12341234dasfdsf",Status.DEU_RUIM);

        novaCompra.finalizaCompra();

        pagamento.foiConcluido();

        Assertions.assertFalse(novaCompra.foiProcessadaComSucesso());

        Assertions.assertFalse(pagamento.foiConcluido());

        Assertions.assertEquals(novaCompra.getStatusCompra(),StatusCompra.FINALIZADA);

    }

    @Test
    @DisplayName("compra é processada com sucesso")
    void test2() {

        var caracteristicasDeProdutos =
                List.of(new CaracteristicasDeProduto("resistente", "com garantia"),
                        new CaracteristicasDeProduto("key1","val2"),
                        new CaracteristicasDeProduto("key2","val3"));

        var produto = new Produto
                ("TENIS",
                        BigDecimal.TEN,
                        2,caracteristicasDeProdutos,
                        "esportivo",
                        new Categoria("esporte"),
                        new Usuario("thiago",
                                new Senha("123456")));

        var novaCompra = new Compra(produto,
                2,"thiagas",
                GatewayPagamento.pagseguro, BigDecimal.TEN);

        var pagamento = new Pagamento(novaCompra,"12341234dasfdsf",Status.CONCLUIDA);

        novaCompra.finalizaCompra();

        pagamento.foiConcluido();

        var collect= novaCompra
                .getPagamentos().stream().filter(Pagamento::foiConcluido).collect(Collectors.toList());


        Assertions.assertTrue(collect.isEmpty());

        Assertions.assertTrue(pagamento.foiConcluido());

        Assertions.assertEquals(novaCompra.getStatusCompra(),StatusCompra.FINALIZADA);

    }
}