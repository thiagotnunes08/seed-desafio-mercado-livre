package br.com.desafio.deveficiente.mercadolivre.produto;

import br.com.desafio.deveficiente.mercadolivre.categoria.CategoriaRepository;
import br.com.desafio.deveficiente.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/produtos")
public class NovoProdutoController {

    @PersistenceContext
    private final EntityManager manager;

    @Autowired
    private final CategoriaRepository categoriaRepository;

    public NovoProdutoController(EntityManager manager, CategoriaRepository categoriaRepository) {
        this.manager = manager;
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    @Transactional
    public void cadastra(@RequestBody @Valid NovoProdutoRequest request,
                         @CurrentSecurityContext(expression = "authentication.principal.attributes")
                         Map<Object, Object> username) {

        String usuarioLogado = (String) username.get("user_name");

        Produto novoProduto = request.toModel(categoriaRepository);

        manager.persist(novoProduto);


    }
}
