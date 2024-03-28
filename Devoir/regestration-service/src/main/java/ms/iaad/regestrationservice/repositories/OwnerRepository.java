package ms.iaad.regestrationservice.repositories;

import ms.iaad.regestrationservice.entites.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
