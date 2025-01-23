package nana.proj.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nana.proj.client.model.*;

@Repository
public interface ClientRepository extends JpaRepository<ClienteModel, Long>{

	ClienteModel findByNome(String nome);

	Long findFirstByOrderByIdDesc();
	}
