package br.com.desafio.deveficiente.mercadolivre.produto;

import br.com.desafio.deveficiente.mercadolivre.categoria.Categoria;
import br.com.desafio.deveficiente.mercadolivre.produto.caracteristicas.CaracteristicasDeProduto;
import br.com.desafio.deveficiente.mercadolivre.usuario.Senha;
import br.com.desafio.deveficiente.mercadolivre.usuario.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.List;

class ProdutoTest {

    @Test
    @DisplayName("um produto precisa de no minimo 3 caracteristicas")
    void test1() {

        CaracteristicasDeProduto caracteristicasDeProduto1
                = new CaracteristicasDeProduto("esporte","para correr");

        CaracteristicasDeProduto caracteristicasDeProduto2
                = new CaracteristicasDeProduto("macio","sola borracha");

        CaracteristicasDeProduto caracteristicasDeProduto3
                = new CaracteristicasDeProduto("leve","parace estar descalco");


        Produto produto =
                new Produto("tenis",
                        BigDecimal.TEN,
                        1,
                        List.of(caracteristicasDeProduto1,caracteristicasDeProduto2,caracteristicasDeProduto3),
                        "uma descricao",new Categoria("ESPORTE"),new Usuario("thiago@123",new Senha("123456")));

        Assertions.assertEquals(produto.getCaracteristicas().size(),3);


    }

    @Test
    @DisplayName("deve dar erro produto com menos de 3 caracteristicas")
    void test2() {

        CaracteristicasDeProduto caracteristicasDeProduto1
                = new CaracteristicasDeProduto("esporte","para correr");

        CaracteristicasDeProduto caracteristicasDeProduto2
                = new CaracteristicasDeProduto("macio","sola borracha");

        Assertions.assertThrows(IllegalArgumentException.class,()-> new Produto("tenis",
                BigDecimal.TEN,
                1,
                List.of(caracteristicasDeProduto1,caracteristicasDeProduto2),
                "uma descricao",new Categoria("ESPORTE"),new Usuario("thiago@123",new Senha("123456"))));


    }

    @DisplayName("não aceita receber inteiro menor doq 0 para abate do estoque")
    @ParameterizedTest
    @CsvSource({"0","-1","-100"})
    void test3(int estoque) throws Exception{

        var caracteristicasDeProdutos = List.of(
                new CaracteristicasDeProduto("key1", "value1"),
                new CaracteristicasDeProduto("key2", "value2"),
                new CaracteristicasDeProduto("key3", "value3"));

        var produto = new Produto("nome"
                ,BigDecimal.TEN,
                10,caracteristicasDeProdutos,
                "muiu bom",
                new Categoria("esporte"),
                new Usuario("thiago@123",new Senha("123456")));

        Assertions.assertThrows(IllegalArgumentException.class,()-> produto.abateValorNoEstoque(estoque));

    }

    @DisplayName("verifica estoque do produto")
    @ParameterizedTest
    @CsvSource({"1,1,true","1,2,false","100000,1,true","0,1,false","1,10000,false"})
    void test4(int estoqueAtual,int estoqueASerAbatido,boolean resultadoEsperado){

        var caracteristicasDeProdutos = List.of(
                new CaracteristicasDeProduto("key1", "value1"),
                new CaracteristicasDeProduto("key2", "value2"),
                new CaracteristicasDeProduto("key3", "value3"));

        var produto = new Produto("nome"
                ,BigDecimal.TEN,
                estoqueAtual,caracteristicasDeProdutos,
                "muiu bom",
                new Categoria("esporte"),
                new Usuario("thiago@123",new Senha("123456")));

        Assertions.assertEquals(produto.abateValorNoEstoque(estoqueASerAbatido),resultadoEsperado);

    }
}