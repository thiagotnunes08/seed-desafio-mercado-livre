package br.com.desafio.deveficiente.mercadolivre.loja;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LojaRespository extends JpaRepository<Loja,Long> {



    boolean existsResponsavel(Long lojaId,Long usuarioId);
}
