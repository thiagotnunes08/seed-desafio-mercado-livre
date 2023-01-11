package br.com.desafio.deveficiente.mercadolivre.compra;

import br.com.desafio.deveficiente.mercadolivre.categoria.Categoria;
import br.com.desafio.deveficiente.mercadolivre.produto.Produto;
import br.com.desafio.deveficiente.mercadolivre.produto.ProdutoRepository;
import br.com.desafio.deveficiente.mercadolivre.produto.caracteristicas.CaracteristicasDeProduto;
import br.com.desafio.deveficiente.mercadolivre.usuario.Senha;
import br.com.desafio.deveficiente.mercadolivre.usuario.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.validation.BindException;

import java.math.BigDecimal;
import java.util.List;

class NovaCompraRequestTest {

    @DisplayName("cria uma nova compra valida, pois abateu o estoque")
    @ParameterizedTest
    @CsvSource({"1,1,true", "0,1,false"})
    void test1(int quantidadeDisponivel, int quantidadeAAbater, boolean resultadoEsperado) throws BindException {


        var caracteristicasDeProdutos = List.of(
                new CaracteristicasDeProduto("key1", "value1"),
                new CaracteristicasDeProduto("key2", "value2"),
                new CaracteristicasDeProduto("key3", "value3"));

        var produto = new Produto("nome"
                , BigDecimal.TEN,
                quantidadeDisponivel, caracteristicasDeProdutos,
                "muiu bom",
                new Categoria("esporte"),
                new Usuario("thiago@123", new Senha("123456")));


        boolean abateu = produto.abateValorNoEstoque(quantidadeAAbater);

        new Compra(produto, quantidadeAAbater, "eu", GatewayPagamento.pagseguro, BigDecimal.TEN);

        Assertions.assertEquals(abateu, resultadoEsperado);

    }

    @Test
    @DisplayName("nao cria uma compra que produto nao foi abatido")
    void test2() {

        var caracteristicasDeProdutos = List.of(
                new CaracteristicasDeProduto("key1", "value1"),
                new CaracteristicasDeProduto("key2", "value2"),
                new CaracteristicasDeProduto("key3", "value3"));

        var produto = new Produto("nome"
                , BigDecimal.TEN,
                100, caracteristicasDeProdutos,
                "muiu bom",
                new Categoria("esporte"),
                new Usuario("thiago@123", new Senha("123456")));

        boolean abateu = produto.abateValorNoEstoque(101);

        Assertions.assertFalse(abateu);

    }

}