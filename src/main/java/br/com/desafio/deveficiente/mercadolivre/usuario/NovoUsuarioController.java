package br.com.desafio.deveficiente.mercadolivre.usuario;
import br.com.desafio.deveficiente.mercadolivre.security.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("api/v1/usuarios")
@RestController
public class NovoUsuarioController {
    @Autowired
    private final UsuarioRepository repository;
    @Autowired
    private final VerificaEmailDuplicadoValidator validator;

    @InitBinder
    public void  init(WebDataBinder binder){
        binder.addValidators(validator);
    }

    public NovoUsuarioController(UsuarioRepository repository, VerificaEmailDuplicadoValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    //funciona de ambas formnas, porém a segunda evita codigo duplicado!
    //@PreAuthorize("hasAuthority('GRAVACAO')")
    @Authorization.Gravacao.PodeEditarDados
    @PostMapping
    public void cadastra(@Valid @RequestBody NovoUsuarioRequest request) {


        Usuario novoUsuario = request.toModel();
        repository.save(novoUsuario);

    }

}