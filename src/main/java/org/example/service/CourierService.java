package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.entity.Courier;
import org.example.entity.Parcel;
import org.example.entity.ParcelStatus;
import org.example.repository.CourierRepository;
import org.example.repository.ParcelRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourierService {

    private final ParcelService parcelService;
    private final CourierRepository courierRepository;
    private final ParcelRepository parcelRepository;

    public List<Courier> getAllCouriers() {
        return courierRepository.findAll();
    }

    public List<Parcel> getAllParcels() {
        return parcelRepository.findAll();
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
        couriers.add(courier1);
        // Parcel 1
        Parcel parcel1 = new Parcel();
        parcel1.setTrackingNumber(123456789L);
        parcel1.setWeightKg(5L);
        parcel1.setStatus(ParcelStatus.IN_TRANSIT);
        parcel1.setDestinationAddress("123 Main St");
        parcels.add(parcel1);
        parcel1.setCourier(courier1);


        // Courier 2
        Courier courier2 = new Courier();
        courier2.setPersonalCode("48007251234");
        courier2.setName("Petras");
        courier2.setLastName("Petraitis");
        courier2.setVehicleNumber("XYZ1561165123");
        couriers.add(courier2);

        // Parcel 2
        Parcel parcel2 = new Parcel();
        parcel2.setTrackingNumber(987654321L);
        parcel2.setStatus(ParcelStatus.DELIVERED);
        parcel2.setWeightKg(5L);
        parcel2.setDestinationAddress("123 Main St");
        parcels.add(parcel2);
        parcel2.setCourier(courier2);

        // Courier 3
        Courier courier3 = new Courier();
        courier3.setPersonalCode("39456789234");
        courier3.setName("Tadas");
        courier3.setLastName("Tadaitis");
        courier3.setVehicleNumber("999KDL");
        couriers.add(courier3);

        // Parcel 3
        Parcel parcel3 = new Parcel();
        parcel3.setTrackingNumber(987654322L);
        parcel3.setWeightKg(5L);
        parcel3.setDestinationAddress("123 Main St");
        parcel3.setStatus(ParcelStatus.DELIVERED);
        parcels.add(parcel3);
        parcel3.setCourier(courier3);

        // Courier 4
        Courier courier4 = new Courier();
        courier4.setPersonalCode("40234567890");
        courier4.setName("Aleksandras");
        courier4.setLastName("Aleksandraitis");
        courier4.setVehicleNumber("XYZ123");
        couriers.add(courier4);

        // Parcel 4
        Parcel parcel4 = new Parcel();
        parcel4.setTrackingNumber(5123546L);
        parcel3.setWeightKg(5L);
        parcel3.setDestinationAddress("123 Main St");
        parcel4.setStatus(ParcelStatus.PENDING);
        parcels.add(parcel4);
        parcel4.setCourier(courier4);

        courierRepository.saveAll(couriers);
        parcelRepository.saveAll(parcels);
    }

}
