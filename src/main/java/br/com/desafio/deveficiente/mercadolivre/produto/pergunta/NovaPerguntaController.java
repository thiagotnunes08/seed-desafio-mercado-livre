package br.com.desafio.deveficiente.mercadolivre.produto.pergunta;

import br.com.desafio.deveficiente.mercadolivre.usuario.Usuario;
import br.com.desafio.deveficiente.mercadolivre.usuario.UsuarioRepository;
import br.com.desafio.deveficiente.mercadolivre.util.EnviaEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping
public class NovaPerguntaController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private EnviaEmail email;

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("produto/perguntas")
    @Transactional
    public String adiciona(@RequestBody @Valid NovaPerguntaRequest request,
                           @CurrentSecurityContext(expression = "authentication.principal.attributes")
                         Map<Object, Object> username) {

        String usuarioLogado = (String) username.get("user_name");


        Usuario usuario = repository.findByLogin(usuarioLogado)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "usuário nao encontrado no sistema!"));


        Pergunta novaPergunta = request.toModel(manager, usuario);

        manager.persist(novaPergunta);

        email.envia(usuarioLogado,"Você tem uma nova pergunta!",novaPergunta.getTitulo());

        novaPergunta.enviaEmail();

      return novaPergunta.toString();


    }
}
