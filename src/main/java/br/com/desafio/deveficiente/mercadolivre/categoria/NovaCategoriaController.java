package br.com.desafio.deveficiente.mercadolivre.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("api/categorias")
public class NovaCategoriaController {

    @PersistenceContext
    private final EntityManager manager;
    @Autowired
    private final CategoriaUnicaValidator validator;

    public NovaCategoriaController(EntityManager manager, CategoriaUnicaValidator validator) {
        this.manager = manager;
        this.validator = validator;
    }

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(validator);

    }

    @PostMapping
    @Transactional
    public String cadastra(@Valid @RequestBody NovaCategoriaRequest request){

         Categoria novaCategoria = request.toModel(manager);

         manager.persist(novaCategoria);

         return novaCategoria.toString();
    }

}
