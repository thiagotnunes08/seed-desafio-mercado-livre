package br.com.desafio.deveficiente.mercadolivre.security;

import br.com.desafio.deveficiente.mercadolivre.loja.LojaRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

/**
 * Classe utilitaria criada para poder extrair claims do token jwt
 */

@Component
public class MercadoLivreSecutiry {

    @Autowired
    private final LojaRespository respository;

    public MercadoLivreSecutiry(LojaRespository respository) {
        this.respository = respository;
    }

    private Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Long getUsuarioId() {
        Jwt jwt = (Jwt) getAuthentication().getPrincipal();
        return jwt.getClaim("usuario_id");
    }

    public boolean gerenciaLoja(Long lojaId){
        return respository.existsResponsavel(lojaId,getUsuarioId());
    }

    //TODO refazer logica client credencials. Aula:23.38 alga works


}
