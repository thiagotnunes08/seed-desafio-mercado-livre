package br.com.desafio.deveficiente.mercadolivre.usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class NovoUsuarioController {
    @Autowired
    private final UsuarioRepository repository;

    public NovoUsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public void cadastra(@Valid @RequestBody NovoUsuarioRequest request) {

        Usuario novoUsuario = request.toModel();
        repository.save(novoUsuario);




    }

}