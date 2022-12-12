package br.com.desafio.deveficiente.mercadolivre.produto;

import br.com.desafio.deveficiente.mercadolivre.produto.caracteristicas.CaracteristicasDeProdutoRequest;
import br.com.desafio.deveficiente.mercadolivre.produto.caracteristicas.ProibeCaracteristicasDuplicadasValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

class ProibeCaracteristicasDuplicadasValidatorTest {

    @ParameterizedTest
    @MethodSource("gerador")
    @DisplayName("nao aceita produto caracteristicas iguais")
    void test1(boolean resultadoEsperado, List<CaracteristicasDeProdutoRequest> caracteristicaRequest) throws Exception {

        NovoProdutoRequest produtoRequest =
                new NovoProdutoRequest("tenis", BigDecimal.TEN,1,caracteristicaRequest,"descricao",1L);

        ProibeCaracteristicasDuplicadasValidator validator = new ProibeCaracteristicasDuplicadasValidator();

        Errors errors = new BeanPropertyBindingResult(produtoRequest,"teste");

        validator.validate(produtoRequest,errors);

        Assertions.assertEquals(resultadoEsperado,errors.hasFieldErrors("caracteristicas"));

    }

    private static Stream<Arguments> gerador(){

        return Stream.of(
                Arguments.of(
                        false,List.of(new CaracteristicasDeProdutoRequest("key","value"))),
                Arguments.of(true,List.of(
                        new CaracteristicasDeProdutoRequest("key","value"),
                        new CaracteristicasDeProdutoRequest("key","value")

                ))
        );
    }
}