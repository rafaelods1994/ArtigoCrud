package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entidades.Pessoa;
import com.example.demo.repo.PessoaRepository;

@RestController
@RequestMapping("/pessoa")
public class ControllerPessoas {

	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping
	public List<Pessoa> getAllPessoas() {
		ArrayList<Pessoa> pessoas = (ArrayList<Pessoa>) pessoaRepository.findAll();
		return pessoas;
	}

	@PostMapping
	public Pessoa createPessoa(@RequestBody Pessoa pessoa) throws Exception {

		Optional<Pessoa> found = pessoaRepository.findByCpf(pessoa.getCpf());

		if (found.isPresent())
			throw new Exception("Pessoa ja cadastrada");

		Pessoa returnValue = pessoaRepository.save(pessoa);

		return returnValue;
	}

	@PutMapping("/{id}")
	public Pessoa updatePessoa(@RequestBody Pessoa pessoa, @PathVariable Long id) throws Exception {

		Optional<Pessoa> found = pessoaRepository.findById(id);

		if (!found.isPresent())
			throw new Exception("Pessoa não encontrada: ");

		pessoa.setID(found.get().getID());

		Pessoa returnValue = pessoaRepository.save(pessoa);

		return returnValue;
	}

	@DeleteMapping("/{id}")
	public void deletePessoa(@PathVariable Long id) throws Exception {

		Optional<Pessoa> found = pessoaRepository.findById(id);
		if (!found.isPresent())
			throw new Exception("ID não encontrado: " + id);

		pessoaRepository.delete(found.get());

	}

}
