package org.example;

import lombok.RequiredArgsConstructor;
import org.example.repository.CourierRepository;
import org.example.service.CourierService;
import org.example.service.ParcelService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@RequiredArgsConstructor
public class Main {
    private final CourierService courierService;
    private final ParcelService parcelService;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {

        System.out.println("Couriers after adding test data:");
        courierService.addTestData();
        System.out.println(courierService.getAllCouriers());

        System.out.println("All Parcels: ");
        System.out.println(parcelService.getAllParcels());

    }
}
