package ms.iaad.infractionservice.repositories;

import ms.iaad.infractionservice.entites.Infraction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfractionRepository extends JpaRepository<Infraction, Long> {
}
