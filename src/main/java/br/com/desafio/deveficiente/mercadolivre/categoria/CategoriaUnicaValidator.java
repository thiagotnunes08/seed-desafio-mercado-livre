package br.com.desafio.deveficiente.mercadolivre.categoria;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class CategoriaUnicaValidator implements Validator {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovaCategoriaRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()){
            return;
        }

        NovaCategoriaRequest request = (NovaCategoriaRequest) target;

        Query query = manager.createQuery("select 1 from Categoria c where c.nome = :nome")
                .setParameter("nome", request.getNome());

        List<?> resultList = query.getResultList();

        if (!resultList.isEmpty()){
            errors.rejectValue("nome",null,"categoria já existe no sistema!");
        }





    }
}
