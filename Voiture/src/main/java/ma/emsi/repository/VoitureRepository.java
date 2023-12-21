package ma.emsi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import feign.Param;
import ma.emsi.model.Voiture;

@Repository
public interface VoitureRepository extends JpaRepository<Voiture, Long> {
	@Query("SELECT v FROM Voiture v WHERE v.id_client = :clientId")
	List<Voiture> getVoitureByClient(@Param("clientId") Long clientId);
}
