package br.com.desafio.deveficiente.mercadolivre.categoria;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RestController
@RequestMapping("api/categorias")
public class DeletaCategoriaController {

    @PersistenceContext
    EntityManager manager;

    @DeleteMapping("/{id}")
    @Transactional
    public void deleta(@PathVariable Long id){

        Categoria categoria = manager.find(Categoria.class, id);

        manager.remove(categoria);



    }
}
