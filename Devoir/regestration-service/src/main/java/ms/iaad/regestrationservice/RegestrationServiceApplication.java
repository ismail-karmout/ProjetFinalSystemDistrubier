package ms.iaad.regestrationservice;

import ms.iaad.regestrationservice.entites.Owner;
import ms.iaad.regestrationservice.entites.Vehicle;
import ms.iaad.regestrationservice.repositories.OwnerRepository;
import ms.iaad.regestrationservice.repositories.VehicleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class RegestrationServiceApplication {

    Random random = new Random();

    public static void main(String[] args) {
        SpringApplication.run(RegestrationServiceApplication.class, args);
    }

    // Add data to H2 database in start of application
    @Bean
    CommandLineRunner start(VehicleRepository vehicleRepository, OwnerRepository ownerRepository) {
        return args -> {
            Stream.of("Hicham lehsou", "Abdelatii hadach ", "Soufiane kaddar",
                    "Ayoub chakal", "ismail karmout").forEach(o -> {

                        String em = o.replaceAll(" ","");
                Owner owner = Owner.builder()
                        .name(o)
                        .email(em+ "@gmail.com")
                        .birthDate(new Date())
                        .build();
                ownerRepository.save(owner);
            });

            ownerRepository.findAll().forEach(owner -> {
                for (int i = 0; i < 3; i++) {
                    Vehicle vehicle = Vehicle.builder()
                            .regestrationNumber(UUID.randomUUID().toString())
                            .fiscalPower(Double.valueOf(random.nextInt(1000)))
                            .brand("brand" + random.nextInt(20))
                            .model("model" + random.nextInt(20))
                            .owner(owner)
                            .build();
                    vehicleRepository.save(vehicle);
                }
            });

        };
    }
}
