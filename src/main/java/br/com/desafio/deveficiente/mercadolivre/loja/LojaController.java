package br.com.desafio.deveficiente.mercadolivre.loja;


import br.com.desafio.deveficiente.mercadolivre.security.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@RestController
@RequestMapping("api/v1/usuarios")
public class LojaController {

    @Autowired
    private final LojaRespository respository;

    public LojaController(LojaRespository respository) {
        this.respository = respository;
    }


    @PatchMapping("/{lojaId}/abrir")
    @Transactional
    @Authorization.GerenciaFuncionamento.AbreOuFechaEstabelecimento
    public void abre(@PathVariable Long lojaId){

        Loja loja = respository.findById(lojaId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        loja.abrirLoja();

    }


    @PatchMapping("/{lojaId}/fechar")
    @Transactional
    @Authorization.GerenciaFuncionamento.AbreOuFechaEstabelecimento
    public void fecha(@PathVariable Long lojaId){

        Loja loja = respository.findById(lojaId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        loja.fecharLoja();

    }

}
