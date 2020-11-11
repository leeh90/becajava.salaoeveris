package br.salaoeveris.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.salaoeveris.app.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {	
	
	Optional<Cliente> findById(Long id);	

}
