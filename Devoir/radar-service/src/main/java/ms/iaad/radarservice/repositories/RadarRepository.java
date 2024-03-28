package ms.iaad.radarservice.repositories;

import ms.iaad.radarservice.entites.Radar;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RadarRepository extends JpaRepository<Radar, Long> {



    Page<Radar> findByNameContains(String name, Pageable pageable);
}
