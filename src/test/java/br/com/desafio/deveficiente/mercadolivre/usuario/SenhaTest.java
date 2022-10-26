package br.com.desafio.deveficiente.mercadolivre.usuario;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SenhaTest {


    @DisplayName("so aceita senha com 6 ou mais caracteres")
    @ParameterizedTest
    @CsvSource({
            "123456", "1234567", "123456789067676778"})
    void test1(String senha) throws Exception {

        new Senha(senha);
    }

    @DisplayName("nao aceita senha menos de 6 caracteres")
    @ParameterizedTest
    @CsvSource({"1","12345","-1","12","123"})
    void test2(String senha) {

        Assertions.assertThrows(IllegalArgumentException.class,
                ()-> new Senha(senha));

    }
}