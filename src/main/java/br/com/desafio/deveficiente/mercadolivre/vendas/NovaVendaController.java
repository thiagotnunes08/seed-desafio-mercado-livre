package br.com.desafio.deveficiente.mercadolivre.vendas;
import br.com.desafio.deveficiente.mercadolivre.compra.CompraRepository;
import br.com.desafio.deveficiente.mercadolivre.usuario.UsuarioRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
public class NovaVendaController {

    private final UsuarioRepository usuarioRepository;
    private final CompraRepository compraRepository;

    private final VendaRepository vendaRepository;

    public NovaVendaController(UsuarioRepository usuarioRepository,
                               CompraRepository compraRepository,
                               VendaRepository vendaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.compraRepository = compraRepository;
        this.vendaRepository = vendaRepository;
    }

    @PostMapping("/vendas")
    public String cria(@Valid @RequestBody VendaRequest request){

        var novaVenda = request.toModel(usuarioRepository,compraRepository);

        vendaRepository.save(novaVenda);

        return novaVenda.toString();

    }
}
