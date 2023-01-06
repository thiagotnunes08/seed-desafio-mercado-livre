package br.com.desafio.deveficiente.mercadolivre.compra;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompraRepository extends JpaRepository<Compra,Long> {
}
