package ma.emsi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ma.emsi.model.Client;
import ma.emsi.model.Voiture;
import ma.emsi.repository.VoitureRepository;
import ma.emsi.service.ClientService;

@Controller
public class VoitureController {

	@Autowired
	VoitureRepository voitureRepository;

	@Autowired
	ClientService clientService;

	@GetMapping("/voitures")
	public ResponseEntity<List<Voiture>> chercherVoitures() {
		List<Voiture> voitures = voitureRepository.findAll();
		List<Voiture> result = new ArrayList<>();
		voitures.forEach(voiture -> {
			Long clientId = voiture.getId_client();
			Client client = clientService.getClientById(clientId);
			voiture.setClient(client);
			result.add(voiture);
		});
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/voitures/client/{id}")
	public ResponseEntity<List<Voiture>> findByClient(@PathVariable Long id) {
		try {
			Client client = clientService.getClientById(id);
			if (client != null) {
				List<Voiture> voitures = voitureRepository.getVoitureByClient(id);
				List<Voiture> result = new ArrayList<>();
				voitures.forEach(voiture -> {
					Long clientId = voiture.getId_client();
					Client c = clientService.getClientById(clientId);
					voiture.setClient(c);
					result.add(voiture);
				});
				return ResponseEntity.ok(result);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/voiture/{id}")
	public Voiture chercherUneVoiture(@PathVariable Long id) throws Exception {
		return this.voitureRepository.findById(id).orElseThrow(() -> new Exception("Voiture inexistante"));
	}

}
