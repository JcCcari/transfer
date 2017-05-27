package com.example.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.example.domain.Transfer;

import java.util.Collection;

@Component
public interface TransferRepository extends CrudRepository<Transfer,Long> {
	@Query("SELECT a FROM Transfer a WHERE a.numeroOperacion = ?1")
	Transfer findByNumero(Long numero);

	// select a.* from account a
	@Query("SELECT a FROM Transfer a")
	Collection<Transfer> findAll();
}
