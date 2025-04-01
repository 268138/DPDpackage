package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class Courier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String personalCode;
    private String name;
    private String lastName;
    private String vehicleNumber;



    @OneToMany(mappedBy = "courier", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude // Prevents infinite recursion in logs
    private List<Parcel> parcels = new ArrayList<>();
}

