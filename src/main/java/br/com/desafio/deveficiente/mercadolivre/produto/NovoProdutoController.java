package br.com.desafio.deveficiente.mercadolivre.produto;

import br.com.desafio.deveficiente.mercadolivre.categoria.CategoriaRepository;
import br.com.desafio.deveficiente.mercadolivre.usuario.Usuario;
import br.com.desafio.deveficiente.mercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/produtos")
public class NovoProdutoController {
    @PersistenceContext
    private final EntityManager manager;
    @Autowired
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private final CategoriaRepository categoriaRepository;

    @InitBinder
    public void init(WebDataBinder binder) {

        binder.addValidators(new ProibeCaracteristicasDuplicadasValidator());
    }

    public NovoProdutoController(EntityManager manager,
                                 UsuarioRepository usuarioRepository,
                                 CategoriaRepository categoriaRepository) {
        this.manager = manager;
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    @Transactional
    public String cadastra(@RequestBody @Valid NovoProdutoRequest request,
                           @CurrentSecurityContext(expression = "authentication.principal.attributes")
                         Map<Object, Object> username) {

        String usuarioLogado = (String) username.get("user_name");

        Usuario dono = usuarioRepository.findByLogin(usuarioLogado)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                                "deveria haver um usuário logado aqui."));

        Produto novoProduto = request.toModel(categoriaRepository, dono);

        manager.persist(novoProduto);

        return novoProduto.toString();

    }
}
