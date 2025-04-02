package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.entity.Courier;
import org.example.entity.Parcel;
import org.example.entity.ParcelStatus;
import org.example.repository.CourierRepository;
import org.example.repository.ParcelRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourierService {

    private final ParcelService parcelService;
    private final CourierRepository courierRepository;
    private final ParcelRepository parcelRepository;
    private static final Logger log = LoggerFactory.getLogger(CourierService.class);

    public List<Courier> getAllCouriers() {
        return courierRepository.findAll();
    }

    public Courier addCourier(Courier courier) {
        log.info("Adding new courier: {}", courier);

        if (courier != null) {
            if (courier.getVehicleNumber() != null) {
                log.info("Courier has vehicle number: {}", courier.getVehicleNumber());
            } else {
                log.warn("Courier does not have a vehicle number. Please make sure it's provided.");
            }
            if (courierRepository.existsByPersonalCode(courier.getPersonalCode())) {
                log.warn("Courier with personal code {} already exists. Skipping addition.", courier.getPersonalCode());
                return null;
            }
        } else {
            log.error("Cannot add a null courier.");
            throw new IllegalArgumentException("Courier cannot be null");
        }

        return courierRepository.saveAndFlush(courier);
    }

    public Courier getCourierByIdOrThrow(Long id) {
        return courierRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Courier with id: " + id + " not found"));
    }

    public Parcel getParcelByIdOrThrow(Long id) {
        return parcelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Parcel with id: " + id + " not found"));
    }

    public void addTestData() {
        List<Courier> couriers = new ArrayList<>();
        List<Parcel> parcels = new ArrayList<>();

        // Courier 1
        Courier courier1 = new Courier();
        courier1.setPersonalCode("39203301111");
        courier1.setName("Jonas");
        courier1.setLastName("Jonaitis");
        courier1.setVehicleNumber("LOL555");

        if (courierRepository.existsByPersonalCode(courier1.getPersonalCode())) {
            log.info("Courier with personal code {} already exists. Skipping addition.", courier1.getPersonalCode());
        } else {
            couriers.add(courier1);

            // Parcel 1
            Parcel parcel1 = new Parcel();
            parcel1.setTrackingNumber(123456789L);
            parcel1.setWeightKg(5L);
            parcel1.setStatus(ParcelStatus.IN_TRANSIT);
            parcel1.setDestinationAddress("123 Main St");

            // setinam parcel1 courier 1
            if (courier1.getId() != null) {
                parcel1.setCourier(courier1);
            }

            parcels.add(parcel1);
        }

        // Courier 2
        Courier courier2 = new Courier();
        courier2.setPersonalCode("48007251234");
        courier2.setName("Petras");
        courier2.setLastName("Petraitis");
        courier2.setVehicleNumber("XYZ1561165123");

        if (courierRepository.existsByPersonalCode(courier2.getPersonalCode())) {
            log.info("Courier with personal code {} already exists. Skipping addition.", courier2.getPersonalCode());
        } else {
            couriers.add(courier2);

            // Parcel 2
            Parcel parcel2 = new Parcel();
            parcel2.setTrackingNumber(987654321L);
            parcel2.setStatus(ParcelStatus.DELIVERED);
            parcel2.setWeightKg(5L);
            parcel2.setDestinationAddress("456 Elm St");

            if (courier2.getId() != null) {
                parcel2.setCourier(courier2);
            }

            parcels.add(parcel2);
        }

        // courier 3
        Courier courier3 = new Courier();
        courier3.setPersonalCode("39456789234");
        courier3.setName("Tadas");
        courier3.setLastName("Tadaitis");
        courier3.setVehicleNumber("999KDL");

        if (courierRepository.existsByPersonalCode(courier3.getPersonalCode())) {
            log.info("Courier with personal code {} already exists. Skipping addition.", courier3.getPersonalCode());
        } else {
            couriers.add(courier3);

            // Parcel 3
            Parcel parcel3 = new Parcel();
            parcel3.setTrackingNumber(987654322L);
            parcel3.setWeightKg(5L);
            parcel3.setDestinationAddress("789 Oak St");
            parcel3.setStatus(ParcelStatus.DELIVERED);

            if (courier3.getId() != null) {
                parcel3.setCourier(courier3);
            }

            parcels.add(parcel3);
        }

        // Courier 4
        Courier courier4 = new Courier();
        courier4.setPersonalCode("40234567890");
        courier4.setName("Aleksandras");
        courier4.setLastName("Aleksandraitis");
        courier4.setVehicleNumber("XYZ123");


        if (courierRepository.existsByPersonalCode(courier4.getPersonalCode())) {
            log.info("Courier with personal code {} already exists. Skipping addition.", courier4.getPersonalCode());
        } else {
            couriers.add(courier4);


            Parcel parcel4 = new Parcel();
            parcel4.setTrackingNumber(5123546L);
            parcel4.setWeightKg(5L);
            parcel4.setDestinationAddress("321 Pine St");
            parcel4.setStatus(ParcelStatus.PENDING);

            if (courier4.getId() != null) {
                log.info("Fetching courier with id: {} for new parcel", courier4.getId());
                parcel4.setCourier(courier4);
            }

            parcels.add(parcel4);
        }

        // Save all couriers and parcels if they aren't duplicates
        log.info("Saving couriers to the repository");
        courierRepository.saveAll(couriers);

        log.info("Saving parcels to the repository");
        parcelRepository.saveAll(parcels);
    }
}