package ma.emsi.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ma.emsi.model.Client;

@FeignClient(name = "SERVICE-CLIENT")
public interface ClientService {
	@GetMapping("/client/{id}")
	Client getClientById(@PathVariable("id") Long id);

}
