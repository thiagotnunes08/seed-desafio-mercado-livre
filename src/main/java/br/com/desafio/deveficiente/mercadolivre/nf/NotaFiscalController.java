package br.com.desafio.deveficiente.mercadolivre.nf;
import br.com.desafio.deveficiente.mercadolivre.compra.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
public class NotaFiscalController {
    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private NotaFiscalRepository notaFiscalRepository;

    @PostMapping("/nf")
    public String cria(@RequestBody @Valid DadosDaCompraRequest request){

        var novaNota = request.toModel(compraRepository);

        notaFiscalRepository.save(novaNota);

        return novaNota.toString();

    }
}
