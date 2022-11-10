package br.com.desafio.deveficiente.mercadolivre.usuario;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class VerificaEmailDuplicadoValidator implements Validator {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovoUsuarioRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        NovoUsuarioRequest request = (NovoUsuarioRequest) target;

        Query query = manager.createQuery("select 1 from Usuario u where u.userName = :login")
                .setParameter("login", request.getUserName());

        List<?> resultList = query.getResultList();

        if (!resultList.isEmpty()){
            errors.rejectValue("login",null,"já existe este email no sistema!");
        }

    }
}
