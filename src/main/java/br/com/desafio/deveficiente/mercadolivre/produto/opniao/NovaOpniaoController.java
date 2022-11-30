package br.com.desafio.deveficiente.mercadolivre.produto.opniao;

import br.com.desafio.deveficiente.mercadolivre.produto.Produto;
import br.com.desafio.deveficiente.mercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@RestController
public class NovaOpniaoController {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private UsuarioRepository repository;


    @PostMapping("produto/opniao")
    @Transactional
    public String opina(@RequestBody @Valid NovaOpiniaoRequest request,
                        @CurrentSecurityContext(expression = "authentication.principal.attributes")
                        Map<Object, Object> username) {

        String usuarioLogado = (String) username.get("user_name");

        Produto produto = Optional.ofNullable(manager
                        .find(Produto.class, request.getProdutoId()))
                .orElseThrow(()
                        -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto nao encotrado no sitema"));

        Opiniao opiniao = request.toModel(usuarioLogado,produto,repository);

        produto.adicionaOpiao(opiniao);

        manager.persist(opiniao);

        return produto.toString();

    }

}
