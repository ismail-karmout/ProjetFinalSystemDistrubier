package ms.iaad.regestrationservice.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Create Vehicle Entity
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String regestrationNumber;
    private String brand;
    private Double fiscalPower;
    private String model;

    @ManyToOne
    private Owner owner;
}
