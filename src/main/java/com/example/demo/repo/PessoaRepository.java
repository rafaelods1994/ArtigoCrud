package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entidades.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, Long> {

	Optional<Pessoa> findByCpf(String cpf);

}
