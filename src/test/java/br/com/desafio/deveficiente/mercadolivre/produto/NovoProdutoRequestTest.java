package br.com.desafio.deveficiente.mercadolivre.produto;

import br.com.desafio.deveficiente.mercadolivre.categoria.NovaCategoriaRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NovoProdutoRequestTest {


    @DisplayName("cria produto com diversas caracteristas")
    @ParameterizedTest
    @MethodSource("gerador")
    void test1(int esperado,List<CaracteristicasDeProdutoRequest> caracteristicasDeProdutoRequests) throws Exception {

        NovoProdutoRequest request = new NovoProdutoRequest("tenis",BigDecimal.TEN,1,
                caracteristicasDeProdutoRequests,"decricao",1L);

        Assertions.assertEquals(esperado,request.buscaCaractreristicas().size());

    }
    private static  Stream<Arguments> gerador(){
        return Stream.of(
                              Arguments.of(0,List.of()),
                              Arguments.of(0,List.of(new CaracteristicasDeProdutoRequest("key","value"))),
                              Arguments.of(0,List.of(new CaracteristicasDeProdutoRequest("key","value"),new CaracteristicasDeProdutoRequest("key1","valu1"))),
                                      Arguments.of(1,List.of(new CaracteristicasDeProdutoRequest("key","value"),
                                        new CaracteristicasDeProdutoRequest("key","value")))

                /**
                 * arguments = 1 : a soma de caracteristicas iguais é igual a 1
                 * exercitanto mc/dc
                 */


        );

    }
}