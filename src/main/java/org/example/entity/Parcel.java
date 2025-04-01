package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Entity
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long trackingNumber; // String
    private Long weightKg; // Double
    private String destinationAddress;

    @Enumerated(EnumType.STRING)
    private ParcelStatus status;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;
}
