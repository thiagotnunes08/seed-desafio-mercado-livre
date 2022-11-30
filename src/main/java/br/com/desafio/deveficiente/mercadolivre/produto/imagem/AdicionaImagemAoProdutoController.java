package br.com.desafio.deveficiente.mercadolivre.produto.imagem;
import br.com.desafio.deveficiente.mercadolivre.produto.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping
public class AdicionaImagemAoProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("produtos/imagens")
    @Transactional
    public String adiciona(@RequestBody @Valid NovaImagemRequest request,
                           @CurrentSecurityContext(expression = "authentication.principal.attributes")
                            Map<Object, Object> username) {

        String usuarioLogado = (String) username.get("user_name");

        Produto produto = Optional.ofNullable(manager.find(Produto.class, request.getProdutoId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Produto nao encotrado no sitema"));

        produto.adicionaEVerificaUser(request.getImagens(),usuarioLogado);

        return produto.toString();


    }
}
