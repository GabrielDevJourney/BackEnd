package com.demoTest.demo.controller;

import com.demoTest.demo.entity.Client;
import com.demoTest.demo.repository.ClientsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Clients")

public class DemoTestController {
	private final ClientsRepository repository;

	public DemoTestController(ClientsRepository repository) {
		this.repository = repository;
	}


	@PostMapping
	public void create(@RequestBody Client client){
		repository.save(client);
	}

	@GetMapping
	public List<Client> getAll(){
		return repository.findAll();
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id){
		repository.deleteById(id);
	}

	@PutMapping
	public Client updateClient(@RequestBody Client client){
		return repository.save(client);
	}

	
}
