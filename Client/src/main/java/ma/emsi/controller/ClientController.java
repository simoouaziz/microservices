package ma.emsi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.emsi.model.Client;
import ma.emsi.repository.ClientRepository;

@RestController
public class ClientController {
	@Autowired
	private ClientRepository clientRepository;

	@GetMapping("/clients")
	public List<Client> findAll() {
		return clientRepository.findAll();
	}

	@GetMapping("/client/{id}")
	public Client findById(@PathVariable Long id) throws Exception {
		return clientRepository.findById(id).orElseThrow(() -> new Exception("Client inexsistant"));
	}

}
