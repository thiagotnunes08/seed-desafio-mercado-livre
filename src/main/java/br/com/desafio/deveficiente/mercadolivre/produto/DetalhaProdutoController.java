package br.com.desafio.deveficiente.mercadolivre.produto;

import br.com.desafio.deveficiente.mercadolivre.produto.pergunta.PerguntaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/produtos")
public class DetalhaProdutoController {

    @PersistenceContext
    private final EntityManager manager;

    @Autowired
    private final PerguntaRepository perguntaRepository;

    public DetalhaProdutoController(EntityManager manager, PerguntaRepository perguntaRepository) {
        this.manager = manager;
        this.perguntaRepository = perguntaRepository;
    }

    @GetMapping("/{id}")
    @Transactional
    public DetalhaProdutoResponse busca(@PathVariable Long id){

        Produto produto = Optional.ofNullable(manager.find(Produto.class, id))
                .orElseThrow(()->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST,"Produto mal informado ou não cadastrado no sistema!"));

        return new DetalhaProdutoResponse(produto,perguntaRepository.findAll());

    }
}
