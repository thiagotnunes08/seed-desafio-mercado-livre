package br.com.desafio.deveficiente.mercadolivre.compra;

import br.com.desafio.deveficiente.mercadolivre.categoria.Categoria;
import br.com.desafio.deveficiente.mercadolivre.pagamento.Pagamento;
import br.com.desafio.deveficiente.mercadolivre.pagamento.RetornoGatewayPagamento;
import br.com.desafio.deveficiente.mercadolivre.pagamento.Status;
import br.com.desafio.deveficiente.mercadolivre.produto.Produto;
import br.com.desafio.deveficiente.mercadolivre.produto.caracteristicas.CaracteristicasDeProduto;
import br.com.desafio.deveficiente.mercadolivre.usuario.Senha;
import br.com.desafio.deveficiente.mercadolivre.usuario.Usuario;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

class CompraTest {

    @Test
    @DisplayName("deveria adicionar uma compra")
    void test1() {


        var caracteristicasDeProdutos =
                List.of(new CaracteristicasDeProduto("resistente", "com garantia"),
                        new CaracteristicasDeProduto("key1", "val2"),
                        new CaracteristicasDeProduto("key2", "val3"));

        var produto = new Produto
                ("TENIS",
                        BigDecimal.TEN,
                        2, caracteristicasDeProdutos,
                        "esportivo",
                        new Categoria("esporte"),
                        new Usuario("thiago",
                                new Senha("123456")));

        var novaCompra = new Compra(produto,
                2, "thiagas",
                GatewayPagamento.pagseguro, BigDecimal.TEN);


        RetornoGatewayPagamento retornoGateway = (compra) -> {
            return new Pagamento(compra,
                    "asidjsai1231", Status.CONCLUIDA);
        };
        novaCompra.adicionaPagamento(retornoGateway);

    }

    @Test
    @DisplayName("nao pode aceitar uma tentativa de pagamento igual")
    void test2() {

        var caracteristicasDeProdutos =
                List.of(new CaracteristicasDeProduto("resistente", "com garantia"),
                        new CaracteristicasDeProduto("key1", "val2"),
                        new CaracteristicasDeProduto("key2", "val3"));

        var produto = new Produto
                ("TENIS",
                        BigDecimal.TEN,
                        2, caracteristicasDeProdutos,
                        "esportivo",
                        new Categoria("esporte"),
                        new Usuario("thiago",
                                new Senha("123456")));

        var novaCompra = new Compra(produto,
                2, "thiagas",
                GatewayPagamento.pagseguro, BigDecimal.TEN);

        RetornoGatewayPagamento retornoGateway = (compra) -> {
            return new Pagamento(novaCompra, "1234jfjs", Status.DEU_RUIM);
        };

        novaCompra.adicionaPagamento(retornoGateway);

        RetornoGatewayPagamento retornoGateway2 = (compra) -> {
            return new Pagamento(novaCompra, "1234jfjs", Status.DEU_RUIM);
        };

        Assertions.assertThrows(IllegalArgumentException.class, () -> novaCompra.adicionaPagamento(retornoGateway2));
    }

    @Test
    @DisplayName("nao pode aceitar uma tentativa de pagamento já concluida")
    void test3() {

        var caracteristicasDeProdutos =
                List.of(new CaracteristicasDeProduto("resistente", "com garantia"),
                        new CaracteristicasDeProduto("key1", "val2"),
                        new CaracteristicasDeProduto("key2", "val3"));

        var produto = new Produto
                ("TENIS",
                        BigDecimal.TEN,
                        2, caracteristicasDeProdutos,
                        "esportivo",
                        new Categoria("esporte"),
                        new Usuario("thiago",
                                new Senha("123456")));

        var novaCompra = new Compra(produto,
                2, "thiagas",
                GatewayPagamento.pagseguro, BigDecimal.TEN);


        RetornoGatewayPagamento retornoGateway = (compra) -> {
            return new Pagamento(novaCompra, "123abc", Status.CONCLUIDA);
        };

        novaCompra.adicionaPagamento(retornoGateway);

        RetornoGatewayPagamento retornoGateway2 = (compra) -> {
            return new Pagamento(novaCompra, "123abcd", Status.CONCLUIDA);
        };

        Assertions.assertThrows(IllegalArgumentException.class, () -> novaCompra.adicionaPagamento(retornoGateway2));

    }

    @ParameterizedTest
    @MethodSource("geradorTest4")
    @DisplayName("deveria verificar se uma compra foi conlcuida com sucesso")
    void test4(boolean resultadoEsperado, Collection<RetornoGatewayPagamento> retornosGateway) {

        var caracteristicasDeProdutos =
                List.of(new CaracteristicasDeProduto("resistente", "com garantia"),
                        new CaracteristicasDeProduto("key1", "val2"),
                        new CaracteristicasDeProduto("key2", "val3"));

        var produto = new Produto
                ("TENIS",
                        BigDecimal.TEN,
                        2, caracteristicasDeProdutos,
                        "esportivo",
                        new Categoria("esporte"),
                        new Usuario("thiago",
                                new Senha("123456")));

        var novaCompra = new Compra(produto,
                2, "thiagas",
                GatewayPagamento.pagseguro, BigDecimal.TEN);


        retornosGateway.forEach(novaCompra::adicionaPagamento);

        Assertions.assertEquals(resultadoEsperado, novaCompra.foiProcessadaComSucesso());
    }

    private static Stream<Arguments> geradorTest4() {
        RetornoGatewayPagamento retornoSucesso1 = (compra) -> {
            return new Pagamento(compra, "1234asc", Status.CONCLUIDA);
        };

        RetornoGatewayPagamento retornoFalha1 = (compra) -> {
            return new Pagamento(compra, "1234asc", Status.DEU_RUIM);
        };

        return Stream.of(
                Arguments.of(true, List.of(retornoSucesso1)),
                Arguments.of(false, List.of(retornoFalha1)),
                Arguments.of(false, List.of())
        );
    }
}