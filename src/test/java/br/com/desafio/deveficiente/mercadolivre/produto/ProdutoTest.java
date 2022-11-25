package br.com.desafio.deveficiente.mercadolivre.produto;

import br.com.desafio.deveficiente.mercadolivre.categoria.Categoria;
import br.com.desafio.deveficiente.mercadolivre.usuario.Senha;
import br.com.desafio.deveficiente.mercadolivre.usuario.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}