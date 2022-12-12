package br.com.desafio.deveficiente.mercadolivre.produto.caracteristicas;

import br.com.desafio.deveficiente.mercadolivre.produto.NovoProdutoRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

public class ProibeCaracteristicasDuplicadasValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return NovoProdutoRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()){
            return;
        }

        NovoProdutoRequest request = (NovoProdutoRequest) target;

        Set<String> nomesIguais = request.buscaCaractreristicas();

        if (!nomesIguais.isEmpty()){
            errors.rejectValue("caracteristicas"
                    ,null,
                    "Não é permitido cadastrar os seguintes nomes de caracteristicas iguais:" + nomesIguais);
        }

    }

}
