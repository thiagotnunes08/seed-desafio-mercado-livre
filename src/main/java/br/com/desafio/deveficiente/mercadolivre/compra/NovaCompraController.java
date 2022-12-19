package br.com.desafio.deveficiente.mercadolivre.compra;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("api/v1/compras")
public class NovaCompraController {

    public void executa(@RequestBody @Valid NovaCompraRequest request){

    }
}
