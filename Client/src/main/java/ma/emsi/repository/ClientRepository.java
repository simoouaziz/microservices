package ma.emsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.emsi.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
