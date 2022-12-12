package br.com.desafio.deveficiente.mercadolivre.produto;

import br.com.desafio.deveficiente.mercadolivre.produto.pergunta.PerguntaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/v1/produtos")
public class BuscaProdutoController {

    @PersistenceContext
    private final EntityManager manager;

    @Autowired
    private final PerguntaRepository perguntaRepository;

    public BuscaProdutoController(EntityManager manager, PerguntaRepository perguntaRepository) {
        this.manager = manager;
        this.perguntaRepository = perguntaRepository;
    }

    @GetMapping("/{id}")
    @Transactional
    public ProdutoResponse busca(@PathVariable Long id){

        Produto produto = manager.find(Produto.class, id);

        return new ProdutoResponse(produto,perguntaRepository.findAll());

    }
}
